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
import org.jscience.mathematics.structure.NormedVectorSpace;

/**
 * <p> A {@link DenseVector dense vector} of 64 bits floating points 
 *     elements.
 * [code]
 * // Creates a float vector of dimension 3.
 * FloatVector V = Vectors.floatVector(0.1, 0.2, 0.3);
 * double x1 = V.getValue(0);
 * double norm = V.normValue();
 * [/code]</p>      
 *      
 * @author <a href="mailto:jean-marie@dautelle.com">Jean-Marie Dautelle</a>
 * @version 5.0, December 12, 2009
 * @see SparseVector
 */
public interface FloatVector extends DenseVector<Float64>,
		NormedVectorSpace<Vector<Float64>, Float64>, ComputeContext.Local {

	/**
	 * Returns the {@code double} value of a single element of this vector.
	 *
	 * @param  i the element index (range [0..dimension[).
	 * @return <code>get(i).doubleValue()</code>.
	 * @throws IndexOutOfBoundsException <code>(i &lt; 0) || (i &gt;= getDimension())</code>
	 */
	double getValue(int i);

	/**
	 * Returns the {@code double} value of the {@link NormedVectorSpace#norm()
	 *  norm} of this vector.
	 *
	 * @return <code>norm().doubleValue()</code>.
	 */
	double normValue();

	@Override
	FloatMatrix asColumn();

	@Override
	FloatMatrix asRow();

	@Override
	FloatVector cross(Vector<Float64> that);

	@Override
	FloatVector getSubVector(List<Index> indices);

	@Override
	FloatVector minus(Vector<Float64> that);

	@Override
	FloatVector opposite();

	@Override
	FloatVector plus(Vector<Float64> that);

	@Override
	FloatMatrix tensor(Vector<Float64> that);

	@Override
	FloatVector times(Float64 k);

}
