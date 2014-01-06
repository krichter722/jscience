/*
 * JScience - Java(TM) Tools and Libraries for the Advancement of Sciences.
 * Copyright (C) 2006 - JScience (http://jscience.org/)
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
import javolution.util.FastTable;
import javolution.util.Index;

import org.jscience.mathematics.structure.Field;
import org.jscience.mathematics.structure.VectorSpace;

/**
 * <p> An immutable element of a vector space.</p>
 *
 * <p> Vector's instances can be produced through factory methods of
 *     the {@link Vectors} class.
 * [code]
 * // Creates a dense vector of 64 bits floating points numbers.
 * Vector<Float64> V0 = Vectors.valueOf(1.1, 1.2, 1.3);
 *
 * // Creates a dense vector of rational numbers.
 * Vector<Rational> V1 = Vectors.valueOf(
 *     Rational.valueOf(23, 45), Rational.valueOf(33, 75));
 *
 * // Creates a sparse vector { null, null, null, 2.1, null, null, null, -7.7 } 
 * // of decimal numbers.
 * Vector<Decimal> V2 = Vectors.valueOf(8, Index.listOf(3, 7),
 *     Decimal.valueOf("2.1"), Decimal.valueOf("-7.7"));
 * [/code]</p>
 * 
 * <p> JScience modules may provide additional vector/matrix specializations.
 * [code]
 * interface Amount<F extends Field<F>, Q extends Quantity> extends Field<Amount<F,?>> {
 *     F value() {...}
 *     Unit<Q> unit() {...}
 * }
 * ... 
 * Amount<Float64, Mass> weight = Amounts.valueOf(83.2, KILOGRAM);
 * Amount<Float64, Duration> time = Amounts.valueOf(12.3, SECOND);
 * AmountVector<Float64> V1 = Amounts.valueOf(weight, time);
 * AmountVector<Float64> V2 = Amounts.valueOf(2, Index.listOf(0), time); // Sparse.
 * AmountMatrix<Float64> M = Amounts.valueOf(V1, V2);
 * [/code]</p>
 * 
 * @author <a href="mailto:jean-marie@dautelle.com">Jean-Marie Dautelle</a>
 * @version 5.0, December 12, 2009
 * @see <a href="http://en.wikipedia.org/wiki/Vector_space">
 *      Wikipedia: Vector Space</a>
 */
public interface Vector<F extends Field<F>> extends VectorSpace<Vector<F>, F> {

	/**
	 * Indicates if all the elements of this vector are different from 
	 * {@code null}.
	 */
	boolean isDense();

	/**
	 * Returns the elements of this vector different from {@code null}.
	 */
	FastTable<F> getData();

	/**
	 * Returns the indices of this vector {@link #getData() data}. 
	 * If this vector {@link #isDense} then 
	 * {@code Index.rangeOf(0, getDimension())} is returned.
	 */
	FastTable<Index> getIndices();

	/**
	 * Returns this vector as a single column matrix.
	 */
	Matrix<F> asColumn();

	/**
	 * Returns this vector as a single row matrix.
	 */
	Matrix<F> asRow();

	/**
	 * Returns this vector as diagonal of a square matrix.
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
