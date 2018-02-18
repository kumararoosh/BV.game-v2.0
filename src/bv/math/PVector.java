/** 
 * @author	Brennan Colberg
 * @since	Dec 19, 2017
 */
package bv.math;

import java.awt.Point;

import bv.syntax.BV;

/** 
 * @author	Brennan Colberg
 * @since	Dec 19, 2017
 */
public class PVector {
	
	/* VARIABLES */
	
	double magnitude;
	double angle;
	
	
	/* CONSTRUCTORS */
	
	public PVector(double newMagnitude, double newAngle) {
		this.setMagnitude(newMagnitude);
		this.setAngle(newAngle);
	}
	
	public PVector(PVector template) {
		this(template.magnitude, template.angle);
	}
	public PVector clone() {
		return new PVector(this);
	}
	
	
	/* CALCULATORS */
	
	// PVector operations
	
	public void add(PVector in) {
		this.subtract(in.toCVector());
	}
	public void subtract(PVector in) {
		this.subtract(in.toCVector());
	}
	public void multiply(PVector in) {
		this.magnitude	*= in.magnitude;
		this.angle		+= in.angle;
	}
	public void divide(PVector in) {
		this.magnitude	/= in.magnitude;
		this.angle		-= in.angle;
	}
	public void scale(double in) {
		this.magnitude *= in;
	}
	public void rotate(double in) {
		this.angle += in;
	}

	public PVector plus(PVector in) {
		return this.plus(in.toCVector());
	}
	public PVector minus(PVector in) {
		return this.minus(in.toCVector());
	}
	public PVector scaledBy(double in) {
		PVector result = new PVector(this);
		result.scale(in);
		return result;
	}
	
	// CVector operations
	
	public void add(CVector in) {
		PVector template = this.toCVector().plus(in).toPVector();
		this.magnitude 	= template.magnitude;
		this.angle 		= template.angle;
	}
	public void subtract(CVector in) {
		PVector template = this.toCVector().minus(in).toPVector();
		this.magnitude 	= template.magnitude;
		this.angle 		= template.angle;
	}

	public PVector plus(CVector in) {
		PVector result = new PVector(this);
		result.add(in);
		return result;
	}
	public PVector minus(CVector in) {
		PVector result = new PVector(this);
		result.subtract(in);
		return result;
	}
	
	
	/* GETTERS & SETTERS */
	
	public double getMagnitude() {
		if (magnitude < 0) this.setMagnitude(this.magnitude);
		return this.magnitude;
	}
	public void setMagnitude(double newMagnitude) {
		this.magnitude = newMagnitude;
	}
	
	public double getAngle() {
		if (angle < 0 || angle > BMath.TAU) this.setAngle(this.angle);
		return this.angle;
	}
	public void setAngle(double newAngle) { // makes sure value is within 0 -> 2pi 
		while (newAngle < 0) newAngle += BV.TAU;
		while (newAngle > BV.TAU) newAngle -= BV.TAU;
		this.angle = newAngle;
	}
	
	
	/* CONVERTERS */

	public PVector(CVector template) {
		this.magnitude = BMath.hypot(template.values[0], template.values[1]);
		this.angle = Math.atan2(template.values[1], template.values[0]);
	}
	public CVector toCVector() {
		return new CVector(this);
	}

	public Point toPoint() {
		return this.toCVector().toPoint();
	}
	
	public String toString() {
		return String.format("(%s at %S radians)", getMagnitude(), getAngle());
	}

}
