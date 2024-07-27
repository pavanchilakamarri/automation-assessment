package com.veevasys.utils;

import org.apache.log4j.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TextFileUtils {

	protected static final Logger log = Logger.getLogger(TextFileUtils.class);
	public static String fileSeparator = System.getProperty("file.separator");
	public String path;

	BufferedWriter bw;
	public TextFileUtils(String path) {
		File file = new File(path);
		FileWriter fw = null;
		try {
			fw = new FileWriter(file.getAbsoluteFile());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		bw = new BufferedWriter(fw);
		this.path=path;
	}

	public  void createFile(File filedata){
		try{
			if (!filedata.exists() && filedata.isFile())
				Files.createFile(Paths.get(path));
		}catch (Exception e){
			log.error("",e);
		}

	}

	public void writeToFile(String text){
		try {

			//System.out.println("WRITING TEXT...");
				bw.write(text);
				//
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void newLine() {
		try {
			bw.newLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	public void closeFile()  {
		try {
			bw.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public String readFile() {
		byte[] encoded = null;
		try {
			encoded = Files.readAllBytes(Paths.get(path));
		} catch (IOException e) {
			log.error("",e);
		}
		return new String(encoded);
	}
	
}
