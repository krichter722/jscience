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
 *     set to zero.</p>
 *           
 * @author <a href="mailto:jean-marie@dautelle.com">Jean-Marie Dautelle</a>
 * @version 5.0, January 26, 2014
 * @see DenseMatrix
 */
public interface SparseMatrix<F extends Field<F>> extends Matrix<F> {

	@Override
	SparseVector<F> getRow(int i);

	@Override
	SparseVector<F> getColumn(int j);

	@Override
	SparseVector<F> getDiagonal();

	@Override
	SparseMatrix<F> getSubMatrix(List<Index> rows, List<Index> columns);

	@Override
	SparseMatrix<F> opposite();

	@Override
	SparseMatrix<F> plus(Matrix<F> that);

	@Override
	SparseMatrix<F> minus(Matrix<F> that);

	@Override
	SparseMatrix<F> times(F k);

	@Override
	SparseVector<F> times(Vector<F> v);

	@Override
	SparseMatrix<F> times(Matrix<F> that);

	@Override
	SparseMatrix<F> inverse();

	@Override
	SparseMatrix<F> divide(Matrix<F> that);

	@Override
	SparseMatrix<F> pseudoInverse();

	@Override
	SparseMatrix<F> transpose();

	@Override
	SparseMatrix<F> adjoint();

	@Override
	SparseVector<F> solve(Vector<F> y);

	@Override
	SparseMatrix<F> solve(Matrix<F> y);

	@Override
	SparseMatrix<F> pow(int exp);

	@Override
	SparseMatrix<F> tensor(Matrix<F> that);

	@Override
	SparseVector<F> vectorization();

}
