/*
 * JScience - Java(TM) Tools and Libraries for the Advancement of Sciences.
 * Copyright (C) 2014 - JScience (http://jscience.org/)
 * All rights reserved.
 * 
 * Permission to use, copy, modify, and distribute this software is
 * freely granted, provided that this notice is preserved.
 */
package org.jscience.mathematics.linear;

import static javolution.lang.Realtime.Limit.LINEAR;
import static javolution.lang.Realtime.Limit.N_SQUARE;

import java.util.Comparator;
import java.util.List;

import javolution.lang.Realtime;
import javolution.lang.ValueType;
import javolution.util.Index;

import org.jscience.mathematics.structure.Field;
import org.jscience.mathematics.structure.VectorSpace;

/**
 * <p> An element of a <a href="http://en.wikipedia.org/wiki/Vector_space">
 *      vector space</a> (immutable).</p>
 *      
 * <p> New vectors' instances can be produced through factory methods of
 *     the {@link Vectors} class (the abstract factory pattern is used 
 *     because several vectors specializations exist).  
 * [code]
 * // Creates a dense vector of 64 bits floating points numbers (GPU accelerated).
 * FloatVector V0 = Vectors.floatVector(1.1, 1.2, 1.3);
 *
 * // Creates a dense vector of 64 bits floating points complex numbers (GPU accelerated).
 * ComplexVector V1 = Vectors.complexVector(new double[]{1.1, 1.2, 1.3}, new double[] {O.1, O.2, O.3});
 * 
 * // Creates a dense vector of rational numbers.
 * DenseVector<Rational> V2 = Vectors.denseVector(Rational.of(23, 45), Rational.of(33, 75));
 *
 * // Creates a sparse vector {0,0,0,2.1,0,0,0,-7.7} of decimal numbers.
 * SparseVector<Decimal> V3 = Vectors.sparseVector(8, Decimal.ZERO, Index.listOf(3, 7), 
 *     Decimal.valueOf("2.1"), Decimal.valueOf("-7.7"));
 *     
 * // Converts a sparse vector to a dense vector.
 * DenseVector<Decimal> V4 = Vectors.denseVector(V3);
 * 
 * // Converts a dense vector to a sparse vector.
 * SparseVector<Rational> V5 = Vectors.sparseVector(V2, Rational.ZERO);
 * [/code]</p>
 * 
 * <p> JScience modules may provide additional vector/matrix specializations.
 * [code]
 * // Physics vector with numerics values all stated using the same unit.
 * abstract class AmountVector<N extends NumericField<N>, Q extends Quantity> implements Vector<Amount<N, ?>> {
 *     static <N extends NumericField<N>, Q extends Quantity> AmountVector<N, Q> of(Vector<N> value, Unit<Q> unit) { ... }
 *     static <Q extends Quantity> AmountVector<Float64, Q> of(Unit<Q> unit, double... elements) { // Convenience method.
 *         return AmountVector.of(Vectors.of(elements), unit);
 *     }
 *     Vector<N> getValue() {...}
 *     Unit<Q> getUnit() {...}
 * }
 * ... 
 * AmountVector<Float64, Velocity> velocity3D = AmountVector.of(METER_PER_SECOND, 1.2, 3.4 -5.7);
 * [/code]</p>
 * 
 * @author <a href="mailto:jean-marie@dautelle.com">Jean-Marie Dautelle</a>
 * @version 5.0, January 26, 2014
 * @see <a href="http://en.wikipedia.org/wiki/Vector_space">Wikipedia: Vector Space</a>
 */
public interface Vector<F extends Field<F>> extends VectorSpace<Vector<F>, F>, ValueType<Vector<F>> {

	/**
	 * Returns this vector as a single column matrix.
	 */
	Matrix<F> asColumn();

	/**
	 * Returns this vector as a single row matrix.
	 */
	Matrix<F> asRow();

	/**
	 * Returns the square matrix having this vector as the diagonal.
	 */
	Matrix<F> asDiagonal();

	/**
	* Indicates if this vector is equal to the object specified.
	*
	* @param  that the object to compare for equality.
	* @return <code>true</code> if this vector and the specified object are
	*         both vectors with equal elements; <code>false</code> otherwise.
	*/
	@Override
	@Realtime(limit = LINEAR)
	boolean equals(Object that);

	/**
	 * Indicates if this vector can be considered equals to the one 
	 * specified using the specified comparator when testing for 
	 * element equality. The specified comparator may allow for some 
	 * tolerance in the difference between the vector elements.
	 *
	 * @param  that the vector to compare for equality.
	 * @param  cmp the comparator to use when testing for element equality.
	 * @return <code>true</code> if this vector and the specified matrix are
	 *         both vector with equal elements according to the specified
	 *         comparator; <code>false</code> otherwise.
	 */
	@Realtime(limit = LINEAR)
	boolean equals(Vector<F> that, Comparator<? super F> cmp);

	/**
	 * Returns a single element from this vector.
	 *
	 * @param  i the element index (range [0..dimension[).
	 * @return the element at <code>i</code>.
	 * @throws IndexOutOfBoundsException <code>(i &lt; 0) || (i &gt;= getDimension())</code>
	 */
	F get(int i);

	/**
	 * Returns the number of elements held by this vector.
	 *
	 * @return this vector dimension.
	 */
	int getDimension();

	/**
	 * Returns the sub-vector formed by the specified elements of this vector.
	 * The indices don't have to be ordered, for example
	 * {@code getSubVector(Index.rangeOf(0, n).reversed())} returns a vector
	 * holding the n first elements of this vector but in reversed order.
	 *
	 * @param indices the indices of the elements making up the sub-vector.
	 * @return the corresponding sub-vector.
	 * @throws IndexOutOfBoundsException if any of the indices is greater
	 *         than this vector dimension.
	 */
	Vector<F> getSubVector(List<Index> indices);

	/**
	 * Returns the cross product of two 3-dimensional vectors.
	 *
	 * @param  that the vector multiplier.
	 * @return <code>this x that</code>
	 * @throws DimensionException if 
	 *         <code>(this.getDimension() != 3) && (that.getDimension() != 3)</code> 
	 */
	Vector<F> cross(Vector<F> that);

	/**
	 * Returns a hash code value for this vector.
	 * Equals objects have equal hash codes.
	 *
	 * @return this vector hash code value.
	 * @see    #equals
	 */
	@Override
	@Realtime(limit = LINEAR)
	int hashCode();

	/**
	 * Returns the difference between this vector and the one specified.
	 *
	 * @param  that the vector to be subtracted.
	 * @return <code>this - that</code>.
	 */
	@Realtime(limit = LINEAR)
	Vector<F> minus(Vector<F> that);

	/**
	 * Returns the negation of this vector.
	 *
	 * @return <code>-this</code>.
	 */
	@Realtime(limit = LINEAR)
	Vector<F> opposite();

	/**
	 * Returns the sum of this vector with the one specified.
	 *
	 * @param   that the vector to be added.
	 * @return  <code>this + that</code>.
	 * @throws  DimensionException is vectors dimensions are different.
	 */
	@Realtime(limit = LINEAR)
	Vector<F> plus(Vector<F> that);

	/**
	 * Returns the tensor product (outer product) of this vector with the one 
	 * specified.
	 *
	 * @param  that the vector multiplier.
	 * @return <code>this &otimes; that</code>
	 * @see <a href="http://en.wikipedia.org/wiki/Tensor_product">
	 *      Wikipedia: Tensor Product</a>
	 */
	@Realtime(limit = N_SQUARE)
	Matrix<F> tensor(Vector<F> that);

	/**
	 * Returns the product of this vector with the specified coefficient.
	 *
	 * @param  k the coefficient multiplier.
	 * @return <code>this · k</code>
	 */
	@Realtime(limit = LINEAR)
	Vector<F> times(F k);

	/**
	 * Returns the dot product (inner product) of this vector with the one 
	 * specified.
	 *
	 * @param  that the vector multiplier.
	 * @return <code>this · that</code>
	 * @throws DimensionException if <code>this.dimension() != that.dimension()</code>
	 * @see <a href="http://en.wikipedia.org/wiki/Dot_product">
	 *      Wikipedia: Dot Product</a>
	 */
	@Realtime(limit = LINEAR)
	F times(Vector<F> that);
}
