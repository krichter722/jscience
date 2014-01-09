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

import org.jscience.mathematics.number.Complex;
import org.jscience.mathematics.number.Float64;

/**
 * <p> A {@link DenseMatrix dense matrix} of 64 bits floating points complex
 *     numbers.</p>
 *           
 * @author <a href="mailto:jean-marie@dautelle.com">Jean-Marie Dautelle</a>
 * @version 5.0, January 26, 2014
 * @see SparseMatrix
 */
public interface ComplexMatrix extends DenseMatrix<Complex<Float64>>,
		ComputeContext.Local {

	@Override
	ComplexVector getRow(int i);

	@Override
	ComplexVector getColumn(int j);

	@Override
	ComplexVector getDiagonal();

	@Override
	ComplexMatrix getSubMatrix(List<Index> rows, List<Index> columns);

	@Override
	ComplexMatrix opposite();

	@Override
	ComplexMatrix plus(Matrix<Complex<Float64>> that);

	@Override
	ComplexMatrix minus(Matrix<Complex<Float64>> that);

	@Override
	ComplexMatrix times(Complex<Float64> k);

	@Override
	ComplexVector times(Vector<Complex<Float64>> v);

	@Override
	ComplexMatrix times(Matrix<Complex<Float64>> that);

	@Override
	ComplexMatrix inverse();

	@Override
	ComplexMatrix divide(Matrix<Complex<Float64>> that);

	@Override
	ComplexMatrix pseudoInverse();

	@Override
	ComplexMatrix transpose();

	@Override
	ComplexMatrix adjoint();

	@Override
	ComplexVector solve(Vector<Complex<Float64>> y);

	@Override
	ComplexMatrix solve(Matrix<Complex<Float64>> y);

	@Override
	ComplexMatrix pow(int exp);

	@Override
	ComplexMatrix tensor(Matrix<Complex<Float64>> that);

	@Override
	ComplexVector vectorization();

}
