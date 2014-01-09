/*
 * JScience - Java(TM) Tools and Libraries for the Advancement of Sciences.
 * Copyright (C) 2014 - JScience (http://jscience.org/)
 * All rights reserved.
 * 
 * Permission to use, copy, modify, and distribute this software is
 * freely granted, provided that this notice is preserved.
 */
package org.jscience.mathematics.linear;

import javolution.util.function.Predicate;

import org.jscience.mathematics.number.Complex;
import org.jscience.mathematics.number.Float64;
import org.jscience.mathematics.structure.Field;

/**
 * <p> Sets of static factory methods to create {@link Matrix} instances.</p>
 *      
 * @author <a href="mailto:jean-marie@dautelle.com">Jean-Marie Dautelle</a>
 * @version 5.0, January 26, 2014
 */
public class Matrices {

	/**
	 * Returns a dense matrix of 64 bits floating points complex numbers 
	 * having the specified rows.
	 */
	public static ComplexMatrix complexMatrix(ComplexVector rows) {
		return null;
	}

	/**
	 * Returns a dense matrix of 64 bits floating points complex numbers 
	 * equivalent to the generic matrix specified.
	 */
	public static ComplexMatrix complexMatrix(Matrix<Complex<Float64>> that) {
		return (that instanceof ComplexMatrix) ? (ComplexMatrix) that : null;
	}

	/**
	 * Returns a dense matrix having the specified rows.
	 */
	public static <F extends Field<F>> DenseMatrix<F> denseMatrix(Vector<F>... rows) {
		return null;
	}

	/**
	 * Returns a dense matrix equivalent to the generic matrix specified.
	 */
	public static <F extends Field<F>> DenseMatrix<F> denseMatrix(Matrix<F> that) {
		return (that instanceof DenseMatrix) ? (DenseMatrix<F>) that : null;
	}

	/**
	 * Returns a dense matrix of 64 bits floating points numbers having 
	 * the specified rows.
	 */
	public static FloatMatrix floatMatrix(FloatVector... rows) {
		return null;
	}

	/**
	 * Returns a dense matrix of 64 bits floating points numbers equivalent 
	 * to the generic matrix specified.
	 */
	public static FloatMatrix floatMatrix(Matrix<Float64> that) {
		return (that instanceof FloatMatrix) ? (FloatMatrix) that : null;
	}

	/**
	 * Returns a sparse matrix having the specified rows.
	 */
	public static <F extends Field<F>> SparseVector<F> sparseVector(Vector<F>... rows) {
		return null;
	}

	/**
	 * Returns a sparse matrix having the specified zero element and equivalent
	 * to the generic matrix specified.
	 */
	public static <F extends Field<F>> SparseMatrix<F> sparseMatrix(
			Matrix<F> that, F zero) {
		return (that instanceof SparseMatrix) ? (SparseMatrix<F>) that : null;
	}

	/**
	 * Returns a sparse matrix having the specified zero element and equivalent
	 * to the generic matrix specified (the equality with zero being tested using 
	 * the specified predicate). If the specified matrix is a sparse matrix
	 * a new sparse matrix can still be returned with more zero elements based 
	 * on the predicate results.
	 */
	public static <F extends Field<F>> SparseMatrix<F> sparseMatrix(
			Matrix<F> that, F zero, Predicate<F> isZero) {
		return null;
	}

	/**
	 * Default constructor (private).
	 */
	private Matrices() {
	}

}
