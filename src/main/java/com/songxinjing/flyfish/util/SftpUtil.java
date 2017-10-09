package com.songxinjing.flyfish.util;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemOptions;
import org.apache.commons.vfs2.Selectors;
import org.apache.commons.vfs2.impl.StandardFileSystemManager;
import org.apache.commons.vfs2.provider.sftp.SftpFileSystemConfigBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SftpUtil {

	protected static final Logger logger = LoggerFactory.getLogger(SftpUtil.class);

	private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);

	static {
		for (int index = 0; index < 10; index++) {
			Runnable sftper = new Runnable() {
				public void run() {
					while (true) {
						try {
							SftpJob job = sftpJobs.take();
							logger.debug("上传图片：" + job.name);
							doFTP(job);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			};
			fixedThreadPool.submit(sftper);
		}
	}

	private static LinkedBlockingQueue<SftpJob> sftpJobs = new LinkedBlockingQueue<SftpJob>();

	private static class SftpJob {
		private String url;
		private String name;

		private SftpJob(String url, String name) {
			this.url = url;
			this.name = name;
		}
	}

	public static void startFTP(String url, String name) {

		SftpJob sftpJob = new SftpJob(url, name);
		try {
			sftpJobs.put(sftpJob);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public static void doFTP(SftpJob sftpJob) {

		StandardFileSystemManager manager = new StandardFileSystemManager();

		try {
			URL fileUrl = new URL(sftpJob.url);

			String serverAddress = "47.91.248.241";
			String userId = "root";
			String password = "Sxj!2345";
			String remoteDirectory = "/flyfish/images";

			// Initializes the file manager
			manager.init();

			// Setup our SFTP configuration
			FileSystemOptions opts = new FileSystemOptions();
			SftpFileSystemConfigBuilder.getInstance().setStrictHostKeyChecking(opts, "no");
			SftpFileSystemConfigBuilder.getInstance().setUserDirIsRoot(opts, true);
			SftpFileSystemConfigBuilder.getInstance().setTimeout(opts, 10000);

			String userInfo = userId + ":" + password;
			String path = remoteDirectory + "/" + sftpJob.name;
			URI sftpUri = new URI("sftp", userInfo, serverAddress, -1, path, null, null);
			FileObject remoteFile = manager.resolveFile(sftpUri.toString(), opts);

			// Create local file object
			FileObject localFile = manager.resolveFile(fileUrl);

			// Copy local file to sftp server
			remoteFile.copyFrom(localFile, Selectors.SELECT_SELF);
		} catch (MalformedURLException | FileSystemException | URISyntaxException e) {
			e.printStackTrace();
		}

	}

}