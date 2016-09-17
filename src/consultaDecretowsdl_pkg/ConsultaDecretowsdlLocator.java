/**
 * ConsultaDecretowsdlLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package consultaDecretowsdl_pkg;

public class ConsultaDecretowsdlLocator extends org.apache.axis.client.Service implements consultaDecretowsdl_pkg.ConsultaDecretowsdl {

    public ConsultaDecretowsdlLocator() {
    }


    public ConsultaDecretowsdlLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ConsultaDecretowsdlLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for consultaDecretowsdlPort
    private java.lang.String consultaDecretowsdlPort_address = "http://www.expedientes.poderjudicial.gub.uy/wsConsultaDecreto.php";

    public java.lang.String getconsultaDecretowsdlPortAddress() {
        return consultaDecretowsdlPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String consultaDecretowsdlPortWSDDServiceName = "consultaDecretowsdlPort";

    public java.lang.String getconsultaDecretowsdlPortWSDDServiceName() {
        return consultaDecretowsdlPortWSDDServiceName;
    }

    public void setconsultaDecretowsdlPortWSDDServiceName(java.lang.String name) {
        consultaDecretowsdlPortWSDDServiceName = name;
    }

    public consultaDecretowsdl_pkg.ConsultaDecretowsdlPortType getconsultaDecretowsdlPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(consultaDecretowsdlPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getconsultaDecretowsdlPort(endpoint);
    }

    public consultaDecretowsdl_pkg.ConsultaDecretowsdlPortType getconsultaDecretowsdlPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            consultaDecretowsdl_pkg.ConsultaDecretowsdlBindingStub _stub = new consultaDecretowsdl_pkg.ConsultaDecretowsdlBindingStub(portAddress, this);
            _stub.setPortName(getconsultaDecretowsdlPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setconsultaDecretowsdlPortEndpointAddress(java.lang.String address) {
        consultaDecretowsdlPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (consultaDecretowsdl_pkg.ConsultaDecretowsdlPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                consultaDecretowsdl_pkg.ConsultaDecretowsdlBindingStub _stub = new consultaDecretowsdl_pkg.ConsultaDecretowsdlBindingStub(new java.net.URL(consultaDecretowsdlPort_address), this);
                _stub.setPortName(getconsultaDecretowsdlPortWSDDServiceName());
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
        if ("consultaDecretowsdlPort".equals(inputPortName)) {
            return getconsultaDecretowsdlPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:consultaDecretowsdl", "consultaDecretowsdl");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:consultaDecretowsdl", "consultaDecretowsdlPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("consultaDecretowsdlPort".equals(portName)) {
            setconsultaDecretowsdlPortEndpointAddress(address);
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
