/*
 * JScience - Java(TM) Tools and Libraries for the Advancement of Sciences.
 * Copyright (C) 2006 - JScience (http://jscience.org/)
 * All rights reserved.
 * 
 * Permission to use, copy, modify, and distribute this software is
 * freely granted, provided that this notice is preserved.
 */
package javax.quantities;
import javax.units.Unit;
import javax.units.SI;

/**
 * This interface represents the extent of something along its greatest 
 * dimension or the extent of space between two objects or places. 
 * The system unit for this quantity is "m" (meter).
 * 
 * @author  <a href="mailto:jean-marie@dautelle.com">Jean-Marie Dautelle</a>
 * @version 1.0, January 14, 2006
 */
public interface Length extends Quantity<Length> {

    /**
     * Holds the SI unit (Système International d'Unités) for this quantity.
     */
    public final static Unit<Length> SI_UNIT = SI.METER;

}