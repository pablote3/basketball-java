package com.rossotti.basketball.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class FileService {
	private final Logger logger = LoggerFactory.getLogger(FileService.class);

	public boolean fileStreamWriter(String fileName, byte[] data) {
		try {
			OutputStream outputStream = new FileOutputStream(fileName, false);
			outputStream.write(data);
			outputStream.close();
			return true;
		}
		catch (FileNotFoundException fnf) {
			throw new FileServiceException("FileNotFoundException");
		}
		catch (IOException ioe) {
			throw new FileServiceException("IOException");
		}
	}

	public String fileLineReader(String fileName) {
		BufferedReader fileReader = null;
		String currentLine = null;
		try {
			fileReader = new BufferedReader(new FileReader(fileName));
			currentLine = fileReader.readLine();
		}
		catch (FileNotFoundException fnf) {
			logger.info("FileNotFoundException reading file = " + fnf);
		}
		catch (IOException ioe) {
			logger.info("IO exception reading file = " + ioe);
		}
		finally {
			try {
				assert fileReader != null;
				fileReader.close();
			}
			catch (IOException ioe) {
				logger.info("IO exception reading file = " + ioe);
			}
		}
		return currentLine;
	}

	public boolean fileExists(String fileName) {
		File file = new File(fileName);
		return file.exists();
	}

	public void fileDelete(String fileName) {
		File file = new File(fileName);
		if (file.exists())
			file.delete();
	}
}