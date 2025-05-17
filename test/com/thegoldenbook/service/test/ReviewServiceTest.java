package com.thegoldenbook.service.test;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.model.Results;
import com.thegoldenbook.model.Review;
import com.thegoldenbook.service.ReviewCriteria;
import com.thegoldenbook.service.ReviewService;
import com.thegoldenbook.service.impl.ReviewServiceImpl;
import com.thegoldenbook.util.DateUtils;

public class ReviewServiceTest {

	private static Logger logger = LogManager.getLogger(ReviewServiceTest.class);
	private ReviewService reviewService = null;

	public ReviewServiceTest() {
		reviewService = new ReviewServiceImpl();
	}

	public void testFindByReview() throws Exception {
	    logger.traceEntry("Testing findByReview...");
	    Review review = reviewService.findByReview(2L, 3L, "en_US");
	    if (review == null) {
	        logger.trace("No reviews were found using the provided data");
	    } else {
	        logger.info(review);
	    }
	}

	public void testFindByCriteria() throws Exception {
	    logger.traceEntry("Testing findByCriteria...");
	    ReviewCriteria reviewCriteria = new ReviewCriteria();
//	    reviewCriteria.setStartDate(DateUtils.getDateTime(2023, 6, 1, 0, 0, 0)); 
//	    reviewCriteria.setEndDate(DateUtils.getDateTime(2025, 8, 25, 0, 0, 0));
	    
	    reviewCriteria.setLocale("es_ES");
	    
	    reviewCriteria.setAscDesc(Boolean.TRUE);
	    Results<Review> results = reviewService.findByReviewCriteria(reviewCriteria, 1, 5);

	    if (results.getPage().isEmpty()) {
	        logger.trace("No reviews were found that match the provided text criteria");
	    } else {
	        for (Review review : results.getPage()) {
	            logger.info(review);
	        }
	    }
	}

	public void testFindByUser() throws Exception {
	    logger.traceEntry("Testing findByUserId...");
	    Results<Review> results = reviewService.findByUser(2L, 1, 5, "es_ES");

	    if (results.getPage().isEmpty()) {
	        logger.trace("No reviews were found using the provided client ID");
	    } else {
	        for (Review review : results.getPage()) {
	            logger.info(review);
	        }
	    }
	}

	public void testFindByBook() throws Exception {
	    logger.traceEntry("Testing findByBookId...");
	    Results<Review> results = reviewService.findByBook(2L, 1, 5, "es_ES");

	    if (results.getPage().isEmpty()) {
	        logger.trace("No reviews were found using the provided book ID");
	    } else {
	        for (Review review : results.getPage()) {
	            logger.info(review);
	        }
	    }
	}


	public void testCreate() throws Exception{
		logger.traceEntry("Testing create...");
		
		String locale = "es_ES";
		
		Review review = new Review();
		review.setUserId(12l);
		review.setBookId(1l);
		review.setRating(4.0d);
		review.setSubject("Una buena lectura");
		review.setBody("Un libro muy interesante!");
		review.setPublicationDate(new Date(System.currentTimeMillis()));
		review.setLanguageId(2);
		reviewService.create(review, locale);
		logger.info("Review created correctly "+review);
	}

	public void testDelete() throws Exception {
	    logger.traceEntry("Testing delete...");
	    if (reviewService.delete(12L, 1L)) {
	        logger.trace("Review deleted successfully");
	    } else {
	        logger.trace("The review was not deleted successfully");
	    }
	}

	public void testUpdate() throws Exception {
	    logger.traceEntry("Testing update...");
	    Review review = reviewService.findByReview(1L, 12L, "es_ES");
	    review.setBody("Es un buen libro");
	    
	    if (reviewService.update(review)) {
	        logger.trace("The review was updated successfully");
	    } else {
	        logger.trace("The review was not updated");
	    }
	}
	
	public void testCalculateAverage() throws Exception{
		Results<Review> reviews = reviewService.findByBook(1l, 1, Integer.MAX_VALUE, "es_ES");
		double averageRating = reviewService.calculateAverage(reviews.getPage());
		logger.trace(averageRating);
	}

	public static void main(String [] args) throws Exception{
		ReviewServiceTest test = new ReviewServiceTest();
		//test.testFindByReview();
		//test.testFindByCriteria();
		//test.testFindByUser();
		//test.testFindByBook();
		//test.testCreate();
		//test.testDelete();
		//test.testUpdate();
		test.testCalculateAverage();;
	}
}
