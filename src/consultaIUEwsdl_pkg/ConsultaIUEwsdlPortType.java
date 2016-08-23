/**
 * ConsultaIUEwsdlPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package consultaIUEwsdl_pkg;

public interface ConsultaIUEwsdlPortType extends java.rmi.Remote {

    /**
     * Dada una iue devuelve los datos de la sede
     */
    public consultaIUEwsdl_pkg.Resultado consultaIUE(java.lang.String iue) throws java.rmi.RemoteException;
}
