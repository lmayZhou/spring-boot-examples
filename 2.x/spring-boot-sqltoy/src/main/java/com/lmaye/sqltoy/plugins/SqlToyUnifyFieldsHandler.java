package com.lmaye.sqltoy.plugins;

import org.sagacity.sqltoy.plugins.IUnifyFieldsHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * -- SqlToyUnifyFieldsHandler
 *
 * @author Lmay Zhou
 * @date 2022/11/30 16:16
 * @email lmay@lmaye.com
 * @since JDK17
 */
public class SqlToyUnifyFieldsHandler implements IUnifyFieldsHandler {
    /**
     * 新增填充字段
     *
     * @return Map<String, Object>
     */
    @Override
    public Map<String, Object> createUnifyFields() {
        Map<String, Object> data = new HashMap<>(2);
        LocalDateTime now = LocalDateTime.now();
        data.put("createAt", now);
        data.put("updateAt", now);
        // TODO 获取用户ID
        data.put("createBy", 0);
        data.put("updateBy", 0);
        data.put("status", 0);
        data.put("version", 1);
        return data;
    }

    /**
     * 更新填充字段
     *
     * @return Map<String, Object>
     */
    @Override
    public Map<String, Object> updateUnifyFields() {
        Map<String, Object> data = new HashMap<>(2);
        data.put("updateAt", LocalDateTime.now());
        // TODO 获取用户ID
        data.put("updateBy", 0);
        return data;
    }
}
