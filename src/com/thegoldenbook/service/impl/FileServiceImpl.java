package com.thegoldenbook.service.impl;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.TheGoldenBookException;
import com.thegoldenbook.config.ConfigurationParametersManager;
import com.thegoldenbook.model.User;
import com.thegoldenbook.model.Language;
import com.thegoldenbook.model.LibroDTO;
import com.thegoldenbook.service.ClienteService;
import com.thegoldenbook.service.FileService;
import com.thegoldenbook.service.IdiomaService;
import com.thegoldenbook.service.LibroService;

public class FileServiceImpl implements FileService{
	
	private static final String BASE_PATH = "base.image.path";
	private static final String BASE_PROFILE_IMAGE_PATH = "base.profile.image.path";
	
	private static Logger logger = LogManager.getLogger(FileServiceImpl.class);
	private LibroService libroService = new LibroServiceImpl();
	private IdiomaService idiomaService = new IdiomaServiceImpl();
	private ClienteService clienteService = new ClienteServiceImpl();
	
	@Override
	public BufferedImage createThumbnail(BufferedImage image, int width, int height) {
		
		Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		bufferedImage.getGraphics().drawImage(scaledImage, 0, 0, null);

		return bufferedImage;
	}
	
	
	 @Override
	    public BufferedImage scaleImage(File imageFile, int targetWidth, int targetHeight) throws IOException {
	        BufferedImage originalImage = ImageIO.read(imageFile);
	        int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

	        // Escalado suave para una mejor calidad
	        Image scaledImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);

	        // Convertir la imagen escalada a BufferedImage
	        BufferedImage bufferedImage = new BufferedImage(targetWidth, targetHeight, type);
	        bufferedImage.getGraphics().drawImage(scaledImage, 0, 0, null);

	        return bufferedImage;
	    }
	
	public Image iconToImage(Icon icon) {
	    if (icon instanceof ImageIcon) {
	        return ((ImageIcon) icon).getImage();
	    } else {
	        int w = icon.getIconWidth();
	        int h = icon.getIconHeight();
	        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	        Graphics g = image.getGraphics();
	        icon.paintIcon(null, g, 0, 0);
	        g.dispose();
	        return image;
	    }
	}

	public Icon scaleIcon(Icon icon, int width, int height) {
	    Image image = iconToImage(icon);
	    Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	    return new ImageIcon(scaledImage);
	}

	public List<File> getImagesByBookId(String locale, Long libroId) {
		 List<File> imageFiles = new ArrayList<>();
		    try {
		       
		        LibroDTO libro = libroService.findByLibro(locale, libroId);
		        Language idioma = idiomaService.findById(locale, libro.getIdiomaId());

		       
		        File folder = new File(ConfigurationParametersManager.getParameterValue(BASE_PATH)+ File.separator + libro.getId() + File.separator + idioma.getIso639().toUpperCase());

		       
		        File[] filesInFolder = folder.listFiles();

		        if (filesInFolder != null) {
		            for (File file : filesInFolder) {
		                if (file.isFile() && file.getName().matches("g\\d+\\.(jpg|png)")) {
		                    imageFiles.add(file);
		                }
		            }
		        }
		    } catch (Exception e) {
		        logger.error(e.getMessage(), e);
		    }
		    return imageFiles;
	}
	
	public List<File> getProfileImageByClienteId(Long clienteId) {
		
		List<File> images = new ArrayList<File>();
		
		User cliente = new User();
		
		try {
			cliente = clienteService.findById(clienteId);
		}catch(TheGoldenBookException pe) {
			logger.error(pe.getMessage(), pe);
		}
		
		File folder = new File(ConfigurationParametersManager.getParameterValue(BASE_PROFILE_IMAGE_PATH)+ File.separator + cliente.getId());
		
		File[] filesInFolder = folder.listFiles();
		if(filesInFolder != null) {
			for(File file : filesInFolder) {
				if(file.isFile() && file.getName().matches("g\\d+\\.(jpg|png)")){
					images.add(file);
				}
			}
		}
		return images;
	}
	
	
	private int getNextImageNumber(File isoFolder) {
		int maxNumber = 0;
		File[] files = isoFolder.listFiles((dir, name) -> name.matches("g\\d+\\.(jpg|png)"));
		if(files != null) {
			for(File file : files) {
				String name = file.getName();
				int number = Integer.parseInt(name.substring(1, name.indexOf('.')));
				if(number > maxNumber) {
					maxNumber = number;
				}
			}
		}
		return maxNumber + 1;
	}

	@Override
	public void uploadImages(LibroDTO libro, String locale, List<File> selectedFiles) {
	    if (selectedFiles != null && !selectedFiles.isEmpty()) {
	        try {
	            Language idioma = idiomaService.findById(locale, libro.getIdiomaId());
	            
	            String baseDirectory = ConfigurationParametersManager.getParameterValue(BASE_PATH);
	            File bookFolder = new File(baseDirectory, libro.getId().toString());
	            
	            if (!bookFolder.exists()) {
	                bookFolder.mkdir();
	            }
	            
	            File isoFolder = new File(bookFolder, idioma.getIso639());
	            if (!isoFolder.exists()) {
	                isoFolder.mkdir();
	            }
	            
	            for (File selectedFile : selectedFiles) {
	                int nextImageNumber = getNextImageNumber(isoFolder);
	                String newFileName = "g" + nextImageNumber + ".jpg";
	                
	                File newFile = new File(isoFolder, newFileName);
	                
	                Files.copy(selectedFile.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
	            }
	        } catch (Exception e) {
	            logger.error(e.getMessage(), e);
	        }
	    }
		
	}
	
	public void uploadProfileImage (Long clienteId, byte[] arrayImage) throws FileNotFoundException, IOException{
		
			Path path = Paths.get(ConfigurationParametersManager.getParameterValue(BASE_PROFILE_IMAGE_PATH)+ File.separator + clienteId + File.separator + "g1.jpg");
			
			if(!Files.exists(path.getParent())) {
				Files.createDirectory(path.getParent());
			}
			
			Files.write(path, arrayImage);

	}

}
