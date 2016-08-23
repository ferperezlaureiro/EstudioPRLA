/**
 * ConsultaIUEwsdlLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package consultaIUEwsdl_pkg;

public class ConsultaIUEwsdlLocator extends org.apache.axis.client.Service implements consultaIUEwsdl_pkg.ConsultaIUEwsdl {

    public ConsultaIUEwsdlLocator() {
    }


    public ConsultaIUEwsdlLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ConsultaIUEwsdlLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for consultaIUEwsdlPort
    private java.lang.String consultaIUEwsdlPort_address = "http://www.expedientes.poderjudicial.gub.uy/wsConsultaIUE.php";

    public java.lang.String getconsultaIUEwsdlPortAddress() {
        return consultaIUEwsdlPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String consultaIUEwsdlPortWSDDServiceName = "consultaIUEwsdlPort";

    public java.lang.String getconsultaIUEwsdlPortWSDDServiceName() {
        return consultaIUEwsdlPortWSDDServiceName;
    }

    public void setconsultaIUEwsdlPortWSDDServiceName(java.lang.String name) {
        consultaIUEwsdlPortWSDDServiceName = name;
    }

    public consultaIUEwsdl_pkg.ConsultaIUEwsdlPortType getconsultaIUEwsdlPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(consultaIUEwsdlPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getconsultaIUEwsdlPort(endpoint);
    }

    public consultaIUEwsdl_pkg.ConsultaIUEwsdlPortType getconsultaIUEwsdlPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            consultaIUEwsdl_pkg.ConsultaIUEwsdlBindingStub _stub = new consultaIUEwsdl_pkg.ConsultaIUEwsdlBindingStub(portAddress, this);
            _stub.setPortName(getconsultaIUEwsdlPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setconsultaIUEwsdlPortEndpointAddress(java.lang.String address) {
        consultaIUEwsdlPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (consultaIUEwsdl_pkg.ConsultaIUEwsdlPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                consultaIUEwsdl_pkg.ConsultaIUEwsdlBindingStub _stub = new consultaIUEwsdl_pkg.ConsultaIUEwsdlBindingStub(new java.net.URL(consultaIUEwsdlPort_address), this);
                _stub.setPortName(getconsultaIUEwsdlPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("consultaIUEwsdlPort".equals(inputPortName)) {
            return getconsultaIUEwsdlPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:consultaIUEwsdl", "consultaIUEwsdl");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:consultaIUEwsdl", "consultaIUEwsdlPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("consultaIUEwsdlPort".equals(portName)) {
            setconsultaIUEwsdlPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
