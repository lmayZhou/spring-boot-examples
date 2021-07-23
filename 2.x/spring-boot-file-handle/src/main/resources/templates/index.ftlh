<!DOCTYPE html>
<html lang="zh">
    <head>
        <meta charset="UTF-8">
        <title>File Handle Demo</title>
        <link rel="stylesheet" type="text/css" href="css/index.css" />
        <script type="text/javascript" src="js/jquery-3.5.1.js"></script>
    </head>

    <body>
        <fieldset>
            <legend>File Download</legend>
            <form id="downloadBatch" action="/file/downloadBatch" method="get">
                <button id="download">Batch Download</button>
            </form>
        </fieldset>
    </body>

    <script type="text/javascript">
        $(function () {
            $("#download").click(function () {
                $("#downloadBatch").submit();
            });
        });
    </script>
</html>
