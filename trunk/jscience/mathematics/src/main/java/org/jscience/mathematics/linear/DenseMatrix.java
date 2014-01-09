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

import org.jscience.mathematics.structure.Field;

/**
 * <p> A {@link Matrix matrix} having a larger number of elements
 *     different from zero.</p>
 *           
 * @author <a href="mailto:jean-marie@dautelle.com">Jean-Marie Dautelle</a>
 * @version 5.0, January 26, 2014
 * @see SparseMatrix
 */
public interface DenseMatrix<F extends Field<F>> extends Matrix<F> {

	@Override
	DenseVector<F> getRow(int i);

	@Override
	DenseVector<F> getColumn(int j);

	@Override
	DenseVector<F> getDiagonal();

	@Override
	DenseMatrix<F> getSubMatrix(List<Index> rows, List<Index> columns);

	@Override
	DenseMatrix<F> opposite();

	@Override
	DenseMatrix<F> plus(Matrix<F> that);

	@Override
	DenseMatrix<F> minus(Matrix<F> that);

	@Override
	DenseMatrix<F> times(F k);

	@Override
	DenseVector<F> times(Vector<F> v);

	@Override
	DenseMatrix<F> times(Matrix<F> that);

	@Override
	DenseMatrix<F> inverse();

	@Override
	DenseMatrix<F> divide(Matrix<F> that);

	@Override
	DenseMatrix<F> pseudoInverse();

	@Override
	DenseMatrix<F> transpose();

	@Override
	DenseMatrix<F> adjoint();

	@Override
	DenseVector<F> solve(Vector<F> y);

	@Override
	DenseMatrix<F> solve(Matrix<F> y);

	@Override
	DenseMatrix<F> pow(int exp);

	@Override
	DenseMatrix<F> tensor(Matrix<F> that);

	@Override
	DenseVector<F> vectorization();

}
