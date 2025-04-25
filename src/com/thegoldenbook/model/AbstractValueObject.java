package com.thegoldenbook.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public abstract class AbstractValueObject implements ValueObject{
	
	public AbstractValueObject(){
		
	}
	
/**	@Override
	public int hashCode() {
		System.out.println(getClass().getName()+" hashCode "+super.hashCode());
		
		return super.hashCode();
	}
**/
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
