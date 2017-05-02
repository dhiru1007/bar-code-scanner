# bar-code-scanner
This is POC for Scanning From Web Pages using Zxing Android Barcode Scanner. In this project, zero foot print HTTP Server.

On Android, you can invoke Barcode Scanner from a web page and have the result returned to your site via a callback URL. For example, when 01234 is scanned, to have the user return to http://<IP of the server>/products/{CODE}, simply link to a URL like this, where {CODE} is a placeholder for the value of the returned code:

http://zxing.appspot.com/scan?ret=http%3A%2F%2F192.168.0.4%2Fproduct%2F%7BCODE%7D

Note that the URL in the ret= parameter is URL-escaped, and that {CODE} is used as a placeholder for the scanned value (above, it appears as %7BCODE%7D). SCAN_FORMATS, and other parameters, can be set here as well to control the scan behavior. For example is can be used to supply a comma-separated list of format names.

Additional placeholders are available in the URL pattern:

    {CODE}: the formatted content of the barcode, or the raw content (unparsed) if raw=true
    {RAWCODE}: the raw content (unparsed)
    {FORMAT}: the name of the barcode format scanned like UPC_A
    {TYPE}: the name of the type of parsed content, like PRODUCT
    {META}: a string representation of the scan metadata, like {POSSIBLE_COUNTRY=US/CA}

To Run the POC
