package com.hzsmk.common.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.csource.fastdfs.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * fastdfs工具
 */
public class RFds {

    public static Logger _logger = LogManager.getLogger(RFds.class);

    private static RFds instance;

    private static TrackerClient tracker;

    private static TrackerServer trackerServer;

    /**
     * 实例化
     *
     * @return
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static RFds getInstance() {
        if (null == instance) {
            synchronized (RFds.class) {
                if (null == instance) {
                    instance = new RFds();
                }
            }
        }
        return instance;
    }

    public RFds() {
        try {
            String filePath = "config/fastdfs.properties";

            ClientGlobal.init(filePath);
        } catch (Exception e) {
            _logger.error(e);
            throw new RuntimeException("文件服务器配置文件异常");
        }
        tracker = new TrackerClient();
        trackerServer = getTrackerServer();
    }

    private TrackerServer getTrackerServer() {
        try {
            return tracker.getConnection();
        } catch (IOException e) {
            _logger.error(e);
            throw new RuntimeException("文件服务器获取连接异常");
        }
    }

    private StorageClient1 getServer() {
        StorageServer storageServer = null;
        return new StorageClient1(trackerServer, storageServer);
    }

    /**
     * 上传图片获取文件路径
     *
     * @param file 文件对象
     * @return String
     * @throws IOException
     * @throws Exception
     */
    public String uploadFile(File file) {
        return uploadFile(file, file.getName());
    }

    /**
     * 上传图片获取文件路径
     *
     * @param file 文件对象
     * @param name 文件名称
     * @return String
     * @throws IOException
     * @throws Exception
     */
    public String uploadFile(File file, String name) {
        byte[] fileBuff = getFileBuffer(file);
        String fileExtName = getFileExtName(name);
        return send(fileBuff, fileExtName);
    }

    /**
     * 上传图片获取文件路径
     *
     * @param fileBuff 文件字节
     * @param name     文件名
     * @return String
     * @throws IOException
     * @throws Exception
     */
    public String uploadFile(byte[] fileBuff, String name) {
        String fileExtName = getFileExtName(name);
        return send(fileBuff, fileExtName);
    }

    /**
     * 删除文件
     *
     * @param name
     */
    public void delFile(String name) {
        StorageClient1 c = null;
        try {
            c = getServer();
            c.delete_file1(name);
        } catch (Exception e) {
            _logger.error(e);
            // 无论是任何异常，都重新获取一次连接的tracker服务
            trackerServer = getTrackerServer();
            throw new RuntimeException("文件服务器异常");
        }
    }

    /**
     * 上传图片获取文件路径
     *
     * @param fileBuff    文件字节
     * @param fileExtName 文件扩展名
     * @return String
     * @throws IOException
     * @throws Exception
     */
    public String uploadFileOfExtName(byte[] fileBuff, String fileExtName) throws IOException {
        return send(fileBuff, fileExtName);
    }

    private String send(byte[] fileBuff, String fileExtName) {
        String upPath = null;
        StorageClient1 c = null;
        try {
            c = getServer();
            upPath = c.upload_file1(fileBuff, fileExtName, null);
        } catch (Exception e) {
            _logger.error(e);
            // 无论是任何异常，都重新获取一次连接的tracker服务
            trackerServer = getTrackerServer();
            throw new RuntimeException("文件服务器异常");
        } finally {
            if (c != null) {
                c = null;
            }
        }
        return upPath;
    }

    /**
     * 下载文件
     *
     * @param fileName
     * @return
     */
    public String downloadBase64(String fileName) {
        return Base64.encodeBase64String(download(fileName));
    }

    public byte[] download(String fileName) {
        byte[] fileByte = null;
        StorageClient1 c = null;
        try {
            c = getServer();
            if (fileName.startsWith("http")) {
                fileName = fileName.replaceAll("http://", "");
                fileName = fileName.replaceAll("https://", "");
                //去除IP和端口
                fileName = fileName.substring(fileName.indexOf("/") + 1);
            }
            if (fileName.startsWith("/")) {
                fileName = fileName.substring(1);
            }
            String group = fileName.substring(0, fileName.indexOf("/"));
            fileName = fileName.replaceAll(group + "/", "");
            fileByte = c.download_file(group, fileName);
        } catch (Exception e) {
            _logger.error(e);
            // 无论是任何异常，都重新获取一次连接的tracker服务
            trackerServer = getTrackerServer();
            throw new RuntimeException("文件服务器异常");
        } finally {
            if (c != null) {
                c = null;
            }
        }
        return fileByte;
    }

    /**
     * 获取文件扩展名
     *
     * @param name 文件名
     * @return
     */
    private String getFileExtName(String name) {
        String extName = "";
        if (name == null) {
            return extName;
        }
        if (name.contains(".")) {
            extName = name.substring(name.lastIndexOf(".") + 1);
        } else {
            extName = "." + extName;
        }
        return extName;
    }

    /**
     * 获取文件字节
     *
     * @param file
     * @return
     */
    public byte[] getFileBuffer(File file) {
        byte[] fileByte = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            fileByte = new byte[fis.available()];
            fis.read(fileByte);
        } catch (FileNotFoundException e) {
            _logger.error("getFileBuffer FileNotFoundException", e);
        } catch (IOException e) {
            _logger.error("getFileBuffer IOException", e);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    _logger.error(e);
                }
            }
        }
        return fileByte;
    }


    public static void main(String[] args) {

        String fileName = "/group1/M00/00/1C/wKgX011l28aAG5A1AACd41HKgvg500.jpg";
        String base = RFds.getInstance().downloadBase64(fileName);
        System.out.println(base.length());
//        ImgeUtil.byteToFile(Base64.decodeBase64(base), "d://", "a.jpg");
        RFds rFds = RFds.getInstance();
    }
}
