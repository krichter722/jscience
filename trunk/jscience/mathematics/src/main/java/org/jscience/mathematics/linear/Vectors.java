/*
 * JScience - Java(TM) Tools and Libraries for the Advancement of Sciences.
 * Copyright (C) 2014 - JScience (http://jscience.org/)
 * All rights reserved.
 * 
 * Permission to use, copy, modify, and distribute this software is
 * freely granted, provided that this notice is preserved.
 */
package org.jscience.mathematics.linear;

import java.util.List;

import javolution.util.Index;
import javolution.util.function.Predicate;

import org.jscience.mathematics.number.Complex;
import org.jscience.mathematics.number.Float64;
import org.jscience.mathematics.structure.Field;

/**
 * <p> Sets of static factory methods to create {@link Vector} instances.</p>
 *      
 * @author <a href="mailto:jean-marie@dautelle.com">Jean-Marie Dautelle</a>
 * @version 5.0, January 26, 2014
 */
public class Vectors {

	/**
	 * Returns a dense vector of 64 bits floating points complex numbers 
	 * having the specified real and imaginary values.
	 */
	public static ComplexVector complexVector(double[] realValues,
			double[] imaginaryValues) {
		return null;
	}

	/**
	 * Returns a dense vector of 64 bits floating points complex numbers 
	 * having the specified complex elements.
	 */
	public static ComplexVector complexVector(Complex<Float64>... elements) {
		return null;
	}

	/**
	 * Returns a dense vector of 64 bits floating points complex numbers 
	 * equivalent to the generic vector specified.
	 */
	public static ComplexVector complexVector(Vector<Complex<Float64>> that) {
		return (that instanceof ComplexVector) ? (ComplexVector) that : null;
	}

	/**
	 * Returns a dense vector having the specified elements.
	 */
	public static <F extends Field<F>> DenseVector<F> denseVector(F... elements) {
		return null;
	}

	/**
	 * Returns a dense vector equivalent to the generic vector specified.
	 */
	public static <F extends Field<F>> DenseVector<F> denseVector(Vector<F> that) {
		return (that instanceof DenseVector) ? (DenseVector<F>) that : null;
	}

	/**
	 * Returns a dense vector of 64 bits floating points numbers having 
	 * the specified {@code double} values.
	 */
	public static FloatVector floatVector(double... values) {
		return null;
	}

	/**
	 * Returns a dense vector of 64 bits floating points numbers having 
	 * the specified elements.
	 */
	public static FloatVector floatVector(Float64... elements) {
		return null;
	}

	/**
	 * Returns a dense vector of 64 bits floating points numbers equivalent 
	 * to the generic vector specified.
	 */
	public static FloatVector floatVector(Vector<Float64> that) {
		return (that instanceof FloatVector) ? (FloatVector) that : null;
	}

	/**
	 * Returns a sparse vector having the specified data.
	 *
	 * @param dimension the vector dimension.
	 * @param zero the zero value of the sparse vector.
	 * @param indices the indices of the data elements (ordered).
	 * @param data the data elements.
	 * @return the corresponding sparse vector.
	 * @throws IllegalArgumentException if {@code indices.length != data.length}
	 *         or if the indices are not sorted smallest first.
	 */
	public static <F extends Field<F>> SparseVector<F> sparseVector(
			int dimension, F zero, List<Index> indices, F... data) {
		return null;
	}

	/**
	 * Returns a sparse vector having the specified zero element and equivalent
	 * to the generic vector specified.
	 */
	public static <F extends Field<F>> SparseVector<F> sparseVector(
			Vector<F> that, F zero) {
		return (that instanceof SparseVector) ? (SparseVector<F>) that : null;
	}

	/**
	 * Returns a sparse vector having the specified zero element and equivalent
	 * to the generic vector specified (the equality with zero being tested using 
	 * the specified predicate). If the specified vector is a sparse vector
	 * a new sparse vector can still be returned with more zero elements based 
	 * on the predicate results.
	 */
	public static <F extends Field<F>> SparseVector<F> sparseVector(
			Vector<F> that, F zero, Predicate<F> isZero) {
		return null;
	}

	/**
	 * Default constructor (private).
	 */
	private Vectors() {
	}

}
