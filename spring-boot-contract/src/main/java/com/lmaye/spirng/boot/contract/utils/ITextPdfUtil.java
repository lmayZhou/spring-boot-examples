package com.lmaye.spirng.boot.contract.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSignatureAppearance;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.security.*;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.util.List;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * -- ITextPdf 工具类
 *
 * @author lmay.Zhou
 * @date 2020/6/10 13:38 星期三
 * @email lmay_zlm@meten.com
 */
@Slf4j
public class ITextPdfUtil {
    /**
     * 生成pdf
     *
     * @param data
     * @param pdfName
     * @param pngName
     * @param docurx
     * @param docury
     * @return
     * @throws TemplateException
     * @throws IOException
     * @throws Exception
     */
    public static ByteArrayOutputStream processPdf(Map<String, Object> data, String pdfName, String pngName, Float docurx,
                                                   Float docury) throws TemplateException, IOException, Exception {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_0);
        // 设置加载模板的目录
        String ftlUrl = ResourceUtils.getFile("classpath:templates").getPath();
        log.info("pdf模板路径:" + ftlUrl);
        cfg.setDirectoryForTemplateLoading(new File(ftlUrl));
        // 设置编码
        cfg.setDefaultEncoding("UTF-8");
        log.info("从指定的模板目录中加载对应的模板文件");
        // 从指定的模板目录中加载对应的模板文件
        Template temp = cfg.getTemplate(pdfName + ".ftlh");
        data.put("basePath", ftlUrl);
        String fileName = ftlUrl + File.separator + System.currentTimeMillis() + ".html";
        log.info("生成HTML文件名：" + fileName);
        File file = new File(fileName);
        if (!file.exists()) {
            boolean isCreate = file.createNewFile();
            if(!isCreate) {
                return null;
            }
        }
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));
        temp.process(data, out);
        String outputFileName = ftlUrl + File.separator + System.currentTimeMillis() + ".pdf";
        log.info("生成PDF文件名：" + outputFileName);
        Document document;
        if (Objects.isNull(docurx) || Objects.isNull(docury)) {
            //默认设置,横向打印
            document = new Document(PageSize.A4);
        } else {
            document = new Document(new RectangleReadOnly(docurx, docury));
        }
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(outputFileName));
        document.open();
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream(fileName), StandardCharsets.UTF_8);
        document.close();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (!StringUtils.isEmpty(pngName)) {
            /*=============================电子签章 Start===========================================*/
            String keystore = ftlUrl + "/lib/pdf/zrong2.p12";
            // key story密码
            char[] password = "chinatex".toCharArray();
            // 签名完成的pdf
            String signFileName = ftlUrl + File.separator + System.currentTimeMillis() + "sign.pdf";
            // 签章图片
            String chapterPath = ftlUrl + "/lib/pdf/" + pngName + ".png";
            String reason = "理由";
            String location = "位置";
            sign(new FileInputStream(outputFileName), new FileOutputStream(signFileName),
                    new FileInputStream(keystore), password, reason, location, chapterPath);
            /*=============================电子签章 Start==========================================*/
        }
        InputStream is = new FileInputStream(outputFileName);
        int buf;
        while ((buf = is.read()) != -1) {
            byteArrayOutputStream.write(buf);
        }
        byteArrayOutputStream.flush();
        is.close();
        out.close();
        writer.close();
        return byteArrayOutputStream;
    }

    /**
     * 在已经生成的pdf上添加电子签章，生成新的pdf并将其输出出来
     *
     * @param src         需要签章的pdf文件路径
     * @param dest        签完章的pdf文件路径
     * @param p12Stream   p12 路径
     * @param password    密码
     * @param reason      签名的原因，显示在pdf签名属性中
     * @param location    地址
     * @param chapterPath 印章路径
     * @throws GeneralSecurityException GeneralSecurityException
     * @throws IOException              IOException
     * @throws DocumentException        DocumentException
     */
    public static void sign(InputStream src, OutputStream dest, InputStream p12Stream, char[] password, String reason,
                            String location, String chapterPath) throws GeneralSecurityException, IOException, DocumentException {
        //读取keystore ，获得私钥和证书链
        KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(p12Stream, password);
        String alias = (String) ks.aliases().nextElement();
        PrivateKey pk = (PrivateKey) ks.getKey(alias, password);
        Certificate[] chain = ks.getCertificateChain(alias);
        // Creating the reader and the stamper，开始pdf reader
        PdfReader reader = new PdfReader(src);
        // 目标文件输出流
        // 创建签章工具PdfStamper ，最后一个boolean参数
        // false的话，pdf文件只允许被签名一次，多次签名，最后一次有效
        // true的话，pdf可以被追加签名，验签工具可以识别出每次签名之后文档是否被修改
        PdfStamper stamper = PdfStamper.createSignature(reader, dest, '\0', null, false);
        // 获取数字签章属性对象，设定数字签章的属性
        PdfSignatureAppearance appearance = stamper.getSignatureAppearance();
        appearance.setReason(reason);
        appearance.setLocation(location);
        // 设置签名的位置，页码，签名域名称，多次追加签名的时候，签名预名称不能一样
        // 签名的位置，是图章相对于pdf页面的位置坐标，原点为pdf页面左下角
        // 四个参数的分别是，图章左下角x，图章左下角y，图章右上角x，图章右上角y
        appearance.setVisibleSignature(new Rectangle(300, 600, 630, 500), 1, "sig1");
        // 读取图章图片，这个image是IText包的image
        Image image = Image.getInstance(chapterPath);
        appearance.setSignatureGraphic(image);
        appearance.setCertificationLevel(PdfSignatureAppearance.CERTIFIED_NO_CHANGES_ALLOWED);
        // 设置图章的显示方式，如下选择的是只显示图章（还有其他的模式，可以图章和签名描述一同显示）
        appearance.setRenderingMode(PdfSignatureAppearance.RenderingMode.GRAPHIC);
        // 这里的IText提供了2个用于签名的接口，可以自己实现，后边着重说这个实现
        // 摘要算法
        ExternalDigest digest = new BouncyCastleDigest();
        // 签名算法
        ExternalSignature signature = new PrivateKeySignature(pk, DigestAlgorithms.SHA256, null);
        // 调用IText签名方法完成pdf签章CryptoStandard.CMS 签名方式，建议采用这种
        MakeSignature.signDetached(appearance, digest, signature, chain, null, null, null,
                0, MakeSignature.CryptoStandard.CMS);
    }

    public static List<Map<String, ByteArrayOutputStream>> processPdf2(List<Map<String, Object>> dataList, String templateName) {
        List<Map<String, ByteArrayOutputStream>> byteArrayOutputStreams = null;
        try {
            // 设置加载模板的目录
            String ftlUrl = ResourceUtils.getFile("classpath:templates").getPath();
            log.info("pdf模板路径: {}", ftlUrl);
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_0);
            cfg.setDirectoryForTemplateLoading(new File(ftlUrl));
            // 设置编码
            cfg.setDefaultEncoding("UTF-8");
            // 从指定的模板目录中加载对应的模板文件
            Template template = cfg.getTemplate(templateName + ".ftlh");
            String generatePath = ftlUrl + File.separator + "out";
            File outFile = new File(generatePath);
            if(!outFile.exists()) {
                outFile.mkdir();
            }
            byteArrayOutputStreams = new ArrayList<>();
            for (Map<String, Object> data : dataList) {
                data.put("basePath", ftlUrl);
                String fileName = generatePath + File.separator + System.currentTimeMillis() + ".html";
                log.info("生成HTML文件名：{}", fileName);
                File file = new File(fileName);
                if (!file.exists()) {
                    file.createNewFile();
                }
                try(Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));
                ) {
                    template.process(data, out);
                    String pdfName = data.get("courseNo").toString();
                    String outputFileName = generatePath + File.separator + pdfName + ".pdf";
                    log.info("生成PDF文件名：{}", outputFileName);
                    // 默认设置,横向打印
                    Document document = new Document(PageSize.A4);
                    PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(outputFileName));
                    document.open();
                    XMLWorkerHelper.getInstance().parseXHtml(pdfWriter, document, new FileInputStream(fileName), StandardCharsets.UTF_8);
                    document.close();
                    try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                        InputStream inputStream = new FileInputStream(outputFileName)) {
                        int buf;
                        while ((buf = inputStream.read()) != -1) {
                            outputStream.write(buf);
                        }
                        outputStream.flush();
                        pdfWriter.close();
                        Map<String, ByteArrayOutputStream> dt = new HashMap<>(1);
                        dt.put(pdfName, outputStream);
                        byteArrayOutputStreams.add(dt);
                        // 文件删除
                    }
                }
            }
            File[] files = outFile.listFiles();
            if(!Objects.isNull(files)) {
                for (File it : files) {
                    if(it.isFile()) {
                        it.delete();
                        log.info("文件删除: {}", it.getPath() + it.getName());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return byteArrayOutputStreams;
    }

    public static void zipBatchDownload(OutputStream outputStream, List<Map<String, ByteArrayOutputStream>> datas) {
        try(ZipOutputStream zos = new ZipOutputStream(outputStream)) {
            for (Map<String, ByteArrayOutputStream> data : datas) {
                for(String key : data.keySet()) {
                    zos.putNextEntry(new ZipEntry(key + ".pdf"));
                    try(ByteArrayOutputStream out = data.get(key);
                        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray())) {
                        byte[] buffer = new byte[1024];
                        int red;
                        while((red = in.read(buffer)) != -1) {
                            zos.write(buffer, 0, red);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}