/**
 * ConsultaDecretowsdlPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package consultaDecretowsdl_pkg;

public interface ConsultaDecretowsdlPortType extends java.rmi.Remote {

    /**
     * Dada una iue y un nro de decreto, devuelve el texto del mismo
     */
    public java.lang.String consultaDecreto(java.lang.String iue, java.lang.String nro_decreto) throws java.rmi.RemoteException;
}
