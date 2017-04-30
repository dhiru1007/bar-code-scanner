/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.qzip.helper;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import in.innomon.srv.helper.SysTimeHelper;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Logger;

/**
 *
 * @author dhirendra
 */
public class UrlCallBackHandler extends SysTimeHelper {

    private Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Override
    public void handle(HttpExchange hx) throws IOException {

        // extract the sub path, index to the Coherence Cache
        String path = hx.getRequestURI().getPath();
        String itemCode = path.substring(path.lastIndexOf("/") + 1);

        //System.out.println(path.indexOf(webContextPath));
        if (itemCode == null) {
            throw new IOException("Got Null value from Barcode Scanner");
        }

        String resultMsg = "<html>\n"
                + "    <head>\n"
                + "        <title>Zxing Barcode Scanner</title>\n"
                + "        <meta charset=\"UTF-8\">\n"
                + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "        <style type=\"text/css\">\n"
                + "            html{box-sizing:border-box}*,*:before,*:after{box-sizing:inherit}\n"
                + "            html{-ms-text-size-adjust:100%;-webkit-text-size-adjust:100%}body{margin:10}\n"
                + "            .w3-btn,.w3-button{border:none;display:inline-block;outline:0;padding:8px 16px;vertical-align:middle;overflow:hidden;text-decoration:none;color:inherit;background-color:inherit;text-align:center;cursor:pointer;white-space:nowrap}\n"
                + "            .w3-btn:hover{box-shadow:0 8px 16px 0 rgba(0,0,0,0.2),0 6px 20px 0 rgba(0,0,0,0.19)}\n"
                + "            .w3-btn,.w3-button{-webkit-touch-callout:none;-webkit-user-select:none;-khtml-user-select:none;-moz-user-select:none;-ms-user-select:none;user-select:none}   \n"
                + "            .w3-disabled,.w3-btn:disabled,.w3-button:disabled{cursor:not-allowed;opacity:0.3}.w3-disabled *,:disabled *{pointer-events:none}\n"
                + "            .w3-btn.w3-disabled:hover,.w3-btn:disabled:hover{box-shadow:none}\n"
                + "            .w3-blue-grey,.w3-hover-blue-grey:hover,.w3-blue-gray,.w3-hover-blue-gray:hover{color:#fff!important;background-color:#607d8b!important}\n"
                + "        </style>\n"
                + "    </head>\n"
                + "    <body>\n"
                + "        <div style=\"text-align: center\"><h4 style=\"color: blue\">POC for Zxing Barcode Scanner Integration</h4></div>\n"
                + "        <div style=\"text-align: center\"><h4 style=\"color: blue\">Scanned Item Code is = " + itemCode + "</h4></div>\n"
                + "        <div style=\"text-align: center\">\n"
                + "            <a class=\"w3-btn w3-blue-grey\" href=\"http://zxing.appspot.com/scan?ret=http%3A%2F%2F192.168.0.4:8003%2Fproduct%2F%7BCODE%7D\">Scan</a>\n"
                + "        </div> \n"
                + "    </body>\n"
                + "</html>\n";
        
        byte[] content = resultMsg.getBytes();
        int respCode = 200;
        String contentType = "text/html";

        Headers hdr = hx.getResponseHeaders();
        hdr.add("Content-type", contentType);
        hx.sendResponseHeaders(respCode, content.length);
        OutputStream out = hx.getResponseBody();
        out.write(content);
        out.close();
    }

}
