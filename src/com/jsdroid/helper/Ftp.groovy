package com.jsdroid.helper

import org.apache.commons.net.ftp.FTP
import org.apache.commons.net.ftp.FTPClient

/**
 * Ftp功能库
 * jar: commons-net-3.8.0.jar
 */
class Ftp {
    boolean upload(String hostname, int port, String username, String password, String savePath, InputStream inputStream) {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(hostname, port)
            ftpClient.login(username, password)
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE)
            ftpClient.enterLocalPassiveMode()
            ftpClient.setControlEncoding("utf-8")
            ftpClient.storeFile(savePath, inputStream)
            ftpClient.logout()
            ftpClient.disconnect()
            return true
        } catch (Exception ignored) {
        }
        try {
            ftpClient.disconnect()
        } catch (Exception ignored) {
        }
    }

    boolean download(String hostname, int port,
                     String username, String password,
                     String filename, OutputStream saveStream) {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(hostname, port)
            ftpClient.login(username, password)
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE)
            ftpClient.enterLocalPassiveMode()
            ftpClient.setControlEncoding("utf-8")
            ftpClient.retrieveFile(filename, saveStream)
            ftpClient.logout()
            ftpClient.disconnect()
            return true
        } catch (Exception ignored) {
        }
        try {
            ftpClient.disconnect()
        } catch (Exception ignored) {
        }
    }
}
