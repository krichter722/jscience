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
import javolution.context.ComputeContext;

import org.jscience.mathematics.number.Complex;
import org.jscience.mathematics.number.Float64;
import org.jscience.mathematics.structure.NormedVectorSpace;

/**
 * <p> A {@link DenseVector dense vector} of 64 bits floating points 
 *     complex numbers.
 * [code]
 * // Creates a complex vector of dimension 3.
 * FloatVector V = Vectors.floatVector(new double[]{0.1, 0.2, 0.3}, 
 *     new double[]{0.7, 0.7, 0.7});
 * double x1 = V.getRealValue(0);
 * double x2 = V.getImaginaryValue(0);
 * double norm = V.normValue();
 * [/code]</p>      
 *      
 * @author <a href="mailto:jean-marie@dautelle.com">Jean-Marie Dautelle</a>
 * @version 5.0, December 12, 2009
 * @see SparseVector
 */
public interface ComplexVector extends DenseVector<Complex<Float64>>, 
		NormedVectorSpace<Vector<Complex<Float64>>, Complex<Float64>>,
		ComputeContext.Local {

	/**
	 * Returns the {@code double} real value of a single complex number
	 * element of this vector.
	 *
	 * @param  i the element index (range [0..dimension[).
	 * @return <code>get(i).getReal().doubleValue()</code>.
	 * @throws IndexOutOfBoundsException <code>(i &lt; 0) || (i &gt;= getDimension())</code>
	 */
	double getRealValue(int i);
	
	/**
	 * Returns the {@code double} imaginary value of a single complex number
	 * element of this vector.
	 *
	 * @param  i the element index (range [0..dimension[).
	 * @return <code>get(i).getImaginary().doubleValue()</code>.
	 * @throws IndexOutOfBoundsException <code>(i &lt; 0) || (i &gt;= getDimension())</code>
	 */
	double getImaginaryValue(int i);
	
	/**
	 * Returns the {@code double} value of the {@link NormedVectorSpace#norm()
	 *  norm} of this vector.
	 *
	 * @return <code>norm().magnitude()</code>.
	 */
	double normValue();
	
	@Override
	ComplexMatrix asColumn();

	@Override
	ComplexMatrix asRow();

	@Override
	ComplexVector cross(Vector<Complex<Float64>> that);

    @Override
	ComplexVector getSubVector(List<Index> indices);

	@Override
	ComplexVector minus(Vector<Complex<Float64>> that);

	@Override
	ComplexVector opposite();

	@Override
	ComplexVector plus(Vector<Complex<Float64>> that);

	@Override
	ComplexMatrix tensor(Vector<Complex<Float64>> that);

	@Override
	ComplexVector times(Complex<Float64> k);

}
