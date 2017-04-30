/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.qzip.helper;

import in.innomon.srv.helper.VfsGetHelper;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class VfsResourceHelper implements VfsGetHelper {

    private String basePath = null;

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    private Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Override
    public byte[] get(String path) {
        InputStream is = null;
        String fullPath = getBasePath() + path;
        byte[] ret = null;
        try {
            is = getClass().getResourceAsStream(fullPath);
            ret = new byte[is.available()];
            if (is.read(ret) != ret.length) {
                throw new IOException("error reading " + fullPath);
            }
        } catch (IOException ex) {
            log.log(Level.WARNING, null, ex);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ex) {
                    log.log(Level.SEVERE, null, ex);
                }
            }
        }
        return ret;

    }

    public void setLoggerName(String loggerName) {
        log = Logger.getLogger(loggerName);
    }
}
