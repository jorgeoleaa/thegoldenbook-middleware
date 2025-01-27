package com.pinguela.thegoldenbook.service;

import java.io.File;
import java.util.List;

import com.pinguela.thegoldenbook.service.impl.FileServiceImpl;

public class FileServiceTest {
	
	public static void main(String[] args) {
		FileService fileService = new FileServiceImpl();
		List<File> imagenes = fileService.getImagesByBookId("it", 1l);
		System.out.println(imagenes.toString());

	}

}
