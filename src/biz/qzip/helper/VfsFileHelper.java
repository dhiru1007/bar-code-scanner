/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.qzip.helper;

import in.innomon.srv.helper.VfsGetHelper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class VfsFileHelper implements VfsGetHelper {

    private Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private String basePath = null;

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    @Override
    public byte[] get(String path) {
        try {
            return (Files.readAllBytes(Paths.get(basePath + path)));
        } catch (IOException ex) {
            log.log(Level.WARNING, null, ex);
        }
        return null;
    }

    public void setLoggerName(String loggerName) {
        log = Logger.getLogger(loggerName);
    }
}
