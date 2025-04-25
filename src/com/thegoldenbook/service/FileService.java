package com.thegoldenbook.service;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.swing.Icon;

import com.thegoldenbook.model.LibroDTO;

public interface FileService {
	
	public BufferedImage scaleImage(File imageFile, int targetWidth, int targetHeight) throws IOException;
	
	public BufferedImage createThumbnail(BufferedImage image, int width, int height);
	
	public Icon scaleIcon(Icon icon, int width, int height);
	
	public Image iconToImage(Icon icon);
	
	public List<File> getImagesByBookId(String locale, Long libroId);
	
	public void uploadImages(LibroDTO libro, String locale, List<File> selectedFiles);
	
	public List<File> getProfileImageByClienteId(Long clienteId);
	
	public void uploadProfileImage (Long clienteId, byte[] arrayImage) throws FileNotFoundException, IOException;
}
