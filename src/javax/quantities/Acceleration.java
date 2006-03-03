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
 * This interface represents the rate of change of velocity with respect to 
 * time. The system unit for this quantity is "m/s²" (meter per square second).
 * 
 * @author  <a href="mailto:jean-marie@dautelle.com">Jean-Marie Dautelle</a>
 * @version 1.0, January 14, 2006
 */
public interface Acceleration extends Quantity<Acceleration> {

    /**
     * Holds the SI unit (Système International d'Unités) for this quantity.
     */
    @SuppressWarnings("unchecked")
    public final static Unit<Acceleration> SI_UNIT 
         = (Unit<Acceleration>) SI.METER.divide(SI.SECOND.pow(2));

}