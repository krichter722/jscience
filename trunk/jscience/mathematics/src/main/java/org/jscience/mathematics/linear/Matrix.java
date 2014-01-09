/*
 * JScience - Java(TM) Tools and Libraries for the Advancement of Sciences.
 * Copyright (C) 2014 - JScience (http://jscience.org/)
 * All rights reserved.
 * 
 * Permission to use, copy, modify, and distribute this software is
 * freely granted, provided that this notice is preserved.
 */
package org.jscience.mathematics.linear;

import java.util.Comparator;
import java.util.List;

import javolution.lang.ValueType;
import javolution.util.Index;

import org.jscience.mathematics.structure.Field;
import org.jscience.mathematics.structure.Ring;
import org.jscience.mathematics.structure.VectorSpace;

/**
 * <p> A rectangular table of elements of a ring-like algebraic structure
 *     (immutable).</p>
 *     
 * <p> New matrices' instances can be produced through factory methods of
 *     the {@link Matrices} class (the abstract factory pattern is used 
 *     because several matrices specializations exist).  
 * [code]
 * // Creates a dense matrix (2x3) of 64 bits floating points numbers (GPU Accelerated)
 * FloatMatrix M0 = Matrices.floatMatrix(
 *      Vectors.floatVector(1.1, 1.2, 1.3), 
 *      Vectors.floatVector(2.1, 2.2, 2.3));
 *
 * // Creates a dense matrix of 64 bits floating points complex numbers (GPU accelerated).
 * ComplexMatrix M1 = Matrices.complexMatrix(complexVector1, complexVector2);
 * 
 * // Creates a dense matrix of rational numbers.
 * DenseMatrix<Rational> M2 = Matrices.denseMatrix(denseVector1, denseVector2);
 *
 * // Creates a sparse matrix of decimal numbers.
 * SparseMatrix<Decimal> M3 = Matrices.sparseMatrix(sparseVector1, sparseVector2); 
 *     
 * // Converts a sparse matrix to a dense matrix.
 * DenseMatrix<Decimal> M4 = Matrices.denseMatrix(M3);
 * 
 * // Converts a dense matrix to a sparse matrix.
 * SparseMatrix<Rational> M5 = Matrices.sparseMatrix(M2, Rational.ZERO);
 * 
 * // Creates a diagonal matrix from a vector.
 * FloatMatrix M6 = Vectors.floatVector(2.3, 4.5).asDiagonal();
 * ComplexMatrix IDENTITY = Vectors.complexVector(Complex.ONE, Complex.ONE).asDiagonal();
 * [/code]
 *      
 * <p> Non-commutative field multiplication is supported. Invertible square 
 *     matrices may form a non-commutative field (also called a division
 *     ring). In which case this class may be used to resolve system of linear
 *     equations with matrix coefficients.</p>
 *     
 * @author <a href="mailto:jean-marie@dautelle.com">Jean-Marie Dautelle</a>
 * @version 5.0, January 26, 2014
 * @see <a href="http://en.wikipedia.org/wiki/Matrix_%28mathematics%29">
 *      Wikipedia: Matrix (mathematics)</a>
 */
public interface Matrix<F extends Field<F>>
         extends VectorSpace<Matrix<F>,F>, Ring<Matrix<F>>, ValueType<Matrix<F>> {

    /**
     * Returns the number of rows <code>m</code> for this matrix.
     *
     * @return m, the number of rows.
     */
    int getRowDimension();

    /**
     * Returns the number of columns <code>n</code> for this matrix.
     *
     * @return n, the number of columns.
     */
    int getColumnDimension();

    /**
     * Returns a single element from this matrix.
     *
     * @param  i the row index (range [0..m[).
     * @param  j the column index (range [0..n[).
     * @return the element read at [i,j].
     * @throws IndexOutOfBoundsException <code>
     *         ((i &lt; 0) || (i &gt;= m)) || ((j &lt; 0) || (j &gt;= n))</code>
     */
    F get(int i, int j);

    /**
     * Returns the row identified by the specified index of this matrix.
     *
     * @param  i the row index (range [0..m[).
     * @return the vector holding the specified row.
     * @throws IndexOutOfBoundsException <code>(i &lt; 0) || (i gt;= m)</code>
     */
    Vector<F> getRow(int i);

    /**
     * Returns the column identified by the specified index of this matrix.
     *
     * @param  j the column index (range [0..n[).
     * @return the vector holding the specified column.
     * @throws IndexOutOfBoundsException <code>(j &lt; 0) || (j &gt;= n)</code>
     */
    Vector<F> getColumn(int j);

    /**
     * Returns the diagonal vector.
     *
     * @return the vector holding the diagonal elements.
     */
    Vector<F> getDiagonal();

    /**
     * Returns the sub-matrix formed by the elements from the specified
     * rows and columns. The indices don't have to be ordered, for example
     * {@code getSubMatrix(Index.listOf(1,0), Index.rangeOf(0,3))}
     * applied on a 3x3 matrix would result in a 2x3 matrix holding
     * the first and second row exchanged.
     *
     * @return the corresponding sub-matrix.
     * @throws IndexOutOfBoundsException if any of the indices is greater
     *         than the associated dimension.
     */
    Matrix<F> getSubMatrix(List<Index> rows, List<Index> columns);

    /**
     * Returns the negation of this matrix.
     *
     * @return <code>-this</code>.
     */
    Matrix<F> opposite();

    /**
     * Returns the sum of this matrix with the one specified.
     *
     * @param   that the matrix to be added.
     * @return  <code>this + that</code>.
     * @throws  DimensionException matrices's dimensions are different.
     */
    Matrix<F> plus(Matrix<F> that);

    /**
     * Returns the difference between this matrix and the one specified.
     *
     * @param  that the matrix to be subtracted.
     * @return <code>this - that</code>.
     * @throws  DimensionException matrices's dimensions are different.
     */
    Matrix<F> minus(Matrix<F> that);

    /**
     * Returns the product of this matrix by the specified factor.
     *
     * @param  k the coefficient multiplier.
     * @return <code>this · k</code>
     */
    Matrix<F> times(F k);

    /**
     * Returns the product of this matrix by the specified column vector
     * (convenience method).
     *
     * @param  v the column vector.
     * @return <code>this · v</code>
     * @throws DimensionException if <code>
     *         v.getDimension() != this.getNumberOfColumns()<code>
     * @see #times(org.jscience.mathematics.vector.Matrix)
     */
    Vector<F> times(Vector<F> v);

    /**
     * Returns the product of this matrix with the one specified.
     *
     * @param  that the matrix multiplier.
     * @return <code>this · that</code>.
     * @throws DimensionException if <code>
     *         this.getNumberOfColumns() != that.getNumberOfRows()</code>.
     */
    Matrix<F> times(Matrix<F> that);

    /**
     * Returns the inverse of this matrix (must be square).
     * The default implementation returns
     * <code>determinant.inverse().times(this.adjoint())</code>
     *
     * @return <code>1 / this</code>
     * @throws DimensionException if this matrix is not square.
     */
    Matrix<F> inverse();
    
    /**
     * Returns this matrix divided by the one specified.
     *
     * @param  that the matrix divisor.
     * @return <code>this / that</code>.
     * @throws DimensionException if that matrix is not square or dimensions 
     *         do not match.
     */
    Matrix<F> divide(Matrix<F> that);

    /**
     * Returns the inverse or pseudo-inverse if this matrix if not square.
     *
     * @return the inverse or pseudo-inverse of this matrix.
     */
    Matrix<F> pseudoInverse();

    /**
     * Returns the determinant of this matrix. The implementations
     * may uses an expansion by minors (also known as Laplacian) or 
     * LU decomposition ({@link DenseMatrix}).
     *
     * @return this matrix determinant.
     * @throws DimensionException if this matrix is not square.
     */
    F determinant();
    
    /**
     * Returns the transpose of this matrix.
     *
     * @return <code>A'</code>.
     */
    Matrix<F> transpose();

    /**
     * Returns the cofactor of an element in this matrix. It is the value
     * obtained by evaluating the determinant formed by the elements not in
     * that particular row or column.
     *
     * @param  i the row index.
     * @param  j the column index.
     * @return the cofactor of <code>THIS[i,j]</code>.
     * @throws DimensionException matrix is not square or its dimension
     *         is less than 2.
     */
    F cofactor(int i, int j);
    
    /**
     * Returns the adjoint of this matrix. It is obtained by replacing each
     * element in this matrix with its cofactor and applying a + or - sign
     * according (-1)**(i+j), and then finding the transpose of the resulting
     * matrix.
     *
     * @return the adjoint of this matrix.
     * @throws DimensionException if this matrix is not square or if
     *         its dimension is less than 2.
     */
    Matrix<F> adjoint();
    
    /**
     * Indicates if this matrix is square.
     *
     * @return <code>getNumberOfRows() == getNumberOfColumns()</code>
     */
    boolean isSquare();

    /**
     * Solves this matrix for the specified vector (convenience method)
     * 
     * @param  y the vector for which the solution is calculated.
     * @return {@code solve(y.asColumn()).getColumn(0)}
     * @throws DimensionException if that matrix is not square or dimensions 
     *         do not match.
     * @see #solve(org.jscience.mathematics.vector.Matrix)
     */
    Vector<F> solve(Vector<F> y);

    /**
     * Solves this matrix for the specified matrix (returns <code>x</code>
     * such as <code>this · x = y</code>). 
     * 
     * @param  y the matrix for which the solution is calculated.
     * @return <code>x</code> such as <code>this · x = y</code>
     * @throws DimensionException if that matrix is not square or dimensions 
     *         do not match.
     */
    Matrix<F> solve(Matrix<F> y);

    /**
     * Returns this matrix raised at the specified exponent.
     *
     * @param  exp the exponent.
     * @return <code>this<sup>exp</sup></code>
     * @throws DimensionException if this matrix is not square.
     */
    Matrix<F> pow(int exp);
    
    /**
     * Returns the trace of this matrix.
     *
     * @return the sum of the diagonal elements.
     */
    F trace();

    /**
     * Returns the linear algebraic matrix tensor product of this matrix
     * and another (Kronecker product).
     *
     * @param  that the second matrix.
     * @return <code>this &otimes; that</code>
     * @see    <a href="http://en.wikipedia.org/wiki/Kronecker_product">
     *         Wikipedia: Kronecker Product</a>
     */
    Matrix<F> tensor(Matrix<F> that);
    
    /**
     * Returns the vectorization of this matrix. The vectorization of 
     * a matrix is the column vector obtain by stacking the columns of the
     * matrix on top of one another.
     *
     * @return the vectorization of this matrix.
     * @see    <a href="http://en.wikipedia.org/wiki/Vectorization_%28mathematics%29">
     *         Wikipedia: Vectorization.</a>
     */
    Vector<F> vectorization();
    
    /**
     * Indicates if this matrix can be considered equals to the one 
     * specified using the specified comparator when testing for 
     * element equality. The specified comparator may allow for some 
     * tolerance in the difference between the matrix elements.
     *
     * @param  that the matrix to compare for equality.
     * @param  cmp the comparator to use when testing for element equality.
     * @return <code>true</code> if this matrix and the specified matrix are
     *         both matrices with equal elements according to the specified
     *         comparator; <code>false</code> otherwise.
     */
    boolean equals(Matrix<F> that, Comparator<? super F> cmp);
    
    /**
     * Indicates if this matrix is strictly equal to the object specified.
     *
     * @param  that the object to compare for equality.
     * @return <code>true</code> if this matrix and the specified object are
     *         both matrices with equal elements; <code>false</code> otherwise.
     * @see    #equals(Matrix, Comparator)
     */
    @Override
    boolean equals(Object that);
    
    /**
     * Returns a hash code value for this matrix.
     * Equals objects have equal hash codes.
     *
     * @return this matrix hash code value.
     * @see    #equals
     */
    @Override
    int hashCode();
    
}
