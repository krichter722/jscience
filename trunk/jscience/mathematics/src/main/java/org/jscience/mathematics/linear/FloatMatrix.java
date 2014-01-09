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

import javolution.context.ComputeContext;
import javolution.util.Index;

import org.jscience.mathematics.number.Float64;

/**
 * <p> A {@link DenseMatrix dense matrix} of 64 bits floating points elements.</p>
 *           
 * @author <a href="mailto:jean-marie@dautelle.com">Jean-Marie Dautelle</a>
 * @version 5.0, January 26, 2014
 * @see SparseMatrix
 */
public interface FloatMatrix extends DenseMatrix<Float64>, ComputeContext.Local {

	@Override
	FloatVector getRow(int i);

	@Override
	FloatVector getColumn(int j);

	@Override
	FloatVector getDiagonal();

	@Override
	FloatMatrix getSubMatrix(List<Index> rows, List<Index> columns);

	@Override
	FloatMatrix opposite();

	@Override
	FloatMatrix plus(Matrix<Float64> that);

	@Override
	FloatMatrix minus(Matrix<Float64> that);

	@Override
	FloatMatrix times(Float64 k);

	@Override
	FloatVector times(Vector<Float64> v);

	@Override
	FloatMatrix times(Matrix<Float64> that);

	@Override
	FloatMatrix inverse();

	@Override
	FloatMatrix divide(Matrix<Float64> that);

	@Override
	FloatMatrix pseudoInverse();

	@Override
	FloatMatrix transpose();

	@Override
	FloatMatrix adjoint();

	@Override
	FloatVector solve(Vector<Float64> y);

	@Override
	FloatMatrix solve(Matrix<Float64> y);

	@Override
	FloatMatrix pow(int exp);

	@Override
	FloatMatrix tensor(Matrix<Float64> that);

	@Override
	FloatVector vectorization();

}
