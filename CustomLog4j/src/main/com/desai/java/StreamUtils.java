/*
 * Copyright(c) 2005 SmartStream Technologies Ltd. All Rights Reserved.
 *
 * This Software is the confidential and proprietary information
 * of SmartStream Technologies Ltd("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of your license agreement.
 */
package com.desai.java;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Utility for helping to use streams
 */
public class StreamUtils {

	/**
	 * The buffer size for decompression
	 */
	private static final int BUFFER_SIZE = 1024 * 64;

	/**
	 * Close an input stream, ignoring any errors
	 * 
	 * @param is
	 */
	public static void closeQuietly(Closeable closeable) {
		try {
			if (closeable != null) {
				closeable.close();
			}
		} catch (IOException e) {
			// ignore
		}
	}

	/**
	 * Uncompress a byte stream that was compressed using the Inflate/Deflate
	 * classes
	 * 
	 * @param is
	 * @return String
	 * @throws IOException
	 */
	public static String uncompress(InputStream is) throws IOException {
		byte[] bytes = readBytes(is);
		String str = uncompress(bytes);
		return str;
	}

	/**
	 * Uncompress a byte array that was compressed using the Inflate/Deflate
	 * classes
	 * 
	 * @param bytes
	 * @return String
	 * @throws IOException
	 */
	public static String uncompress(byte[] bytes) throws IOException {
		InputStream zin = null;
		ByteArrayOutputStream finalBaos = null;
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
			zin = new InflaterInputStream(bais);
			finalBaos = new ByteArrayOutputStream();
			byte[] buf = new byte[BUFFER_SIZE];
			while (zin.available() != 0) {
				int bytesRead = zin.read(buf);
				if (bytesRead != -1) {
					finalBaos.write(buf, 0, bytesRead);
				}
			}
			zin.close();
			finalBaos.close();
			final String str = new String(finalBaos.toByteArray());
			return str;
		} finally {
			closeQuietly(zin);
			closeQuietly(finalBaos);
		}
	}

	/**
	 * Read all the bytes from this input stream and return them
	 * 
	 * @param zin
	 * @return byte[]
	 * @throws IOException
	 */
	public static byte[] readBytes(InputStream zin) throws IOException {
		ByteArrayOutputStream os = null;
		try {
			os = new ByteArrayOutputStream();

			byte[] buf = new byte[1024 * 64];
			while (zin.available() != 0) {
				int bytesRead = zin.read(buf);
				if (bytesRead != -1) {
					os.write(buf, 0, bytesRead);
				}
			}
			os.close();
			byte[] bytes = os.toByteArray();
			return bytes;
		} finally {
			closeQuietly(os);
		}
	}

	/**
	 * Compress a string
	 * 
	 * @param str
	 * @return byte array, compressed using the deflater algorithum
	 * @throws IOException
	 */
	public static byte[] compress(String str) throws IOException {
		OutputStream zos = null;
		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream();
			// OutputStream zos = new GZIPOutputStream(baos);
			zos = new DeflaterOutputStream(baos);
			zos.write(str.getBytes());
			zos.close();
			baos.close();
			byte[] bytes = baos.toByteArray();
			return bytes;
		} finally {
			closeQuietly(zos);
			closeQuietly(baos);
		}
	}

	/**
	 * Produce a zip from a File artefact
	 * 
	 * @param sourceDirectory
	 * @param targetFile
	 * @throws StreamUtilsException
	 */
	public static void compressDirectoryToZipfile(File sourceDirectory,
			File targetFile) throws Exception {
		compressDirectoryToZipfile(sourceDirectory, targetFile, false);
	}

	/**
	 * Produce a zip from a File artefact
	 * 
	 * @param sourceDirectory
	 * @param targetFile
	 * @param excludeParentDir
	 * @throws StreamUtilsException
	 */
	public static void compressDirectoryToZipfile(File sourceDirectory,
			File targetFile, boolean excludeParentDir) throws Exception {
		ZipOutputStream zos = null;
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(targetFile);
			bos = new BufferedOutputStream(fos);
			zos = new ZipOutputStream(bos);
			if (sourceDirectory.isDirectory()) {
				String pathFromTopDirectory = sourceDirectory.getName();
				if (excludeParentDir) {
					pathFromTopDirectory = null;
				}
				try {
					addDirectoryContentsToZip(zos, sourceDirectory,
							pathFromTopDirectory);
				} catch (FileNotFoundException e) {
					throw new Exception(
							"failed to process zip entries with error '"
									+ e.getMessage() + "'");
				} catch (IOException e) {
					throw new Exception(
							"failed to process zip entries with error '"
									+ e.getMessage() + "'");
				}
			} else {
				throw new Exception("sourceDirectory argument '"
						+ sourceDirectory + "' is not a directory");
			}

		} catch (IOException e) {
			throw new Exception("Error while compressing directory:"
					+ sourceDirectory.getAbsolutePath() + " to zip file:"
					+ targetFile.getAbsolutePath(), e);
		} finally {
			closeQuietly(zos);
			closeQuietly(bos);
			closeQuietly(fos);
		}
	}

	/**
	 * Compress a single file to a zip
	 * 
	 * @param fileToCompress
	 * @param targetFile
	 * @param pathInZipFile
	 *            - non null, pass empty string if the root of the zip file is
	 *            required
	 * @throws StreamUtilsException
	 */
	public static void compressToZipfile(File fileToCompress, File targetFile,
			String pathInZipFile) throws Exception {
		compressToZipfile(Arrays.asList(new File[] { fileToCompress }),
				targetFile, Arrays.asList(new String[] { pathInZipFile }));
	}

	/**
	 * Compress a list of files into a zip
	 * 
	 * @param fileToCompress
	 * @param targetFile
	 * @param pathInZipFile
	 *            - non null, pass empty string if the root of the zip file is
	 *            required
	 * @throws StreamUtilsException
	 */
	public static void compressToZipfile(Collection<File> filesToCompress,
			File targetFile, Collection<String> pathsInZipFile)
			throws Exception {
		if (filesToCompress.size() != pathsInZipFile.size()) {
			throw new Exception(
					"filesToCompress.size() != pathsInZipFile.size() - "
							+ filesToCompress.size() + ", "
							+ pathsInZipFile.size());
		}
		ZipOutputStream zos = null;
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(targetFile);
			bos = new BufferedOutputStream(fos);
			zos = new ZipOutputStream(bos);

			Iterator<String> paths = pathsInZipFile.iterator();
			for (File fileToCompress : filesToCompress) {
				addFileToZip(zos, fileToCompress, paths.next());
			}
		} catch (IOException e) {
			throw new Exception("Error while compressing file:"
					+ filesToCompress + " to zip file:"
					+ targetFile.getAbsolutePath());
		} finally {
			closeQuietly(zos);
			closeQuietly(bos);
			closeQuietly(fos);
		}
	}

	/**
	 * recursively process a directory
	 * 
	 * @param pathFromTopDirectory
	 */
	private static void addDirectoryContentsToZip(ZipOutputStream zos,
			File directory, String pathFromTopDirectory)
			throws FileNotFoundException, IOException {

		File files[] = directory.listFiles();

		for (int i = 0; i < files.length; i++) {
			File file = files[i];

			if (!file.isDirectory()
					|| (file.isDirectory() && (file.list().length == 0))) {
				addFileToZip(zos, file, pathFromTopDirectory);
			} else {
				String newPathFromTopDirectory = pathFromTopDirectory
						+ File.separator + file.getName();
				if (pathFromTopDirectory == null) {
					newPathFromTopDirectory = file.getName();
				}
				addDirectoryContentsToZip(zos, file, newPathFromTopDirectory);
			}
		}
	}

	/**
	 * add a file to a zip stream
	 * 
	 * @param zos
	 * @param file
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static void addFileToZip(ZipOutputStream zos, File file,
			String pathFromTopDirectory) throws FileNotFoundException,
			IOException {

		byte data[] = new byte[BUFFER_SIZE];
		String entryName = pathFromTopDirectory + File.separator
				+ file.getName();
		if (pathFromTopDirectory == null) {
			entryName = file.getName();
		}
		if (file.isDirectory()) {
			entryName += "/";
		}
		ZipEntry entry = new ZipEntry(entryName);
		zos.putNextEntry(entry);

		if (!file.isDirectory()) {
			BufferedInputStream origin = null;
			FileInputStream fi = null;
			try {
				fi = new FileInputStream(file);
				origin = new BufferedInputStream(fi, BUFFER_SIZE);
				int count;
				while ((count = origin.read(data, 0, BUFFER_SIZE)) != -1) {
					zos.write(data, 0, count);
				}
			} finally {
				closeQuietly(origin);
				closeQuietly(fi);
			}
		}
	}

	/**
	 * @param sourceFilePath
	 * @param destinationDirectory
	 * @param pathPrefixToMatch
	 * @throws IOException
	 * @throws StreamUtilsException
	 */
	public static void uncompressZipFileToDirectory(File sourceFilePath,
			File destinationDirectory, String pathPrefixToMatch)
			throws IOException, Exception {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(sourceFilePath);
			uncompressStreamToFile(fis, destinationDirectory, pathPrefixToMatch);
		} finally {
			closeQuietly(fis);
		}
	}

	/**
	 * Uncompress bytes to a directory
	 * 
	 * @param fileContents
	 * @param destinationDirectory
	 * @param pathPrefixToMatch
	 * @throws IOException
	 * @throws StreamUtilsException
	 */
	public static void uncompressZipFileToDirectory(byte[] fileContents,
			File destinationDirectory, String pathPrefixToMatch)
			throws IOException, Exception {
		ByteArrayInputStream bis = new ByteArrayInputStream(fileContents);
		uncompressStreamToFile(bis, destinationDirectory, pathPrefixToMatch);
	}

	private static void uncompressStreamToFile(InputStream is,
			File destinationDirectory, String pathPrefixToMatch)
			throws IOException, Exception {
		ZipInputStream zis = null;
		try {
			zis = new ZipInputStream(is);
			ZipEntry entry;
			if (!destinationDirectory.isDirectory()) {
				throw new Exception(
						"destinationDirectory argument is not a directory '"
								+ destinationDirectory + "'");
			}

			while ((entry = zis.getNextEntry()) != null) {
				if (pathPrefixToMatch == null
						|| entry.getName().startsWith(pathPrefixToMatch)) {
					uncompressEntry(destinationDirectory, zis, entry);
				}
			}
		} finally {
			closeQuietly(zis);
		}
	}

	private static void uncompressEntry(File destinationDirectory,
			ZipInputStream zis, ZipEntry entry) throws FileNotFoundException,
			IOException {
		int count;
		byte data[] = new byte[BUFFER_SIZE];
		// write the files to the disk
		File file = new File(destinationDirectory, entry.getName());
		if (entry.isDirectory()) {
			file.mkdirs();
		} else {
			File parentFile = file.getParentFile();
			parentFile.mkdirs();
			FileOutputStream fos = null;
			BufferedOutputStream dest = null;
			try {
				fos = new FileOutputStream(file);
				dest = new BufferedOutputStream(fos, BUFFER_SIZE);
				while ((count = zis.read(data, 0, BUFFER_SIZE)) != -1) {
					dest.write(data, 0, count);
				}
				dest.close();
				fos.close();
			} finally {
				closeQuietly(dest);
				closeQuietly(fos);
			}
		}
	}

	public static byte[] serialize(Object obj) throws IOException {
		ObjectOutputStream oos = null;
		DeflaterOutputStream dos = null;
		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream();
			dos = new DeflaterOutputStream(baos);
			oos = new ObjectOutputStream(dos);
			oos.writeObject(obj);
			oos.close();
			dos.close();
			baos.close();
			return baos.toByteArray();
		} finally {
			closeQuietly(oos);
			closeQuietly(dos);
			closeQuietly(baos);
		}
	}

	public static Object deserialize(byte[] bytes, final ClassLoader cl)
			throws IOException {
		ByteArrayInputStream is = null;
		InflaterInputStream inflater = null;
		ObjectInputStream ois = null;
		try {
			is = new ByteArrayInputStream(bytes);
			inflater = new InflaterInputStream(is);
			ois = new ObjectInputStream(inflater) {
				@Override
				protected Class<?> resolveClass(ObjectStreamClass desc)
						throws IOException, ClassNotFoundException {
					String name = desc.getName();
					return Class.forName(name, false, cl);
				}
			};
			Object obj = ois.readObject();
			return obj;
		} catch (Throwable t) {
			IOException ioEx = new IOException("Error deserialising object");
			ioEx.initCause(t);
			throw ioEx;
		} finally {
			closeQuietly(ois);
			closeQuietly(inflater);
			closeQuietly(is);
		}
	}

}
