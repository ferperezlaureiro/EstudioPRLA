package consultaDecretowsdl;

import java.rmi.RemoteException;

import consultaDecretowsdl_pkg.*;

public class ConsultaDecretowsdlPortTypeProxy implements ConsultaDecretowsdlPortType {
  private String _endpoint = null;
  private ConsultaDecretowsdlPortType consultaDecretowsdlPortType = null;
  
  public ConsultaDecretowsdlPortTypeProxy() {
    _initConsultaDecretowsdlPortTypeProxy();
  }
  
  public ConsultaDecretowsdlPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initConsultaDecretowsdlPortTypeProxy();
  }
  
  private void _initConsultaDecretowsdlPortTypeProxy() {
    try {
      consultaDecretowsdlPortType = (new ConsultaDecretowsdlLocator()).getconsultaDecretowsdlPort();
      if (consultaDecretowsdlPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)consultaDecretowsdlPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)consultaDecretowsdlPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (consultaDecretowsdlPortType != null)
      ((javax.xml.rpc.Stub)consultaDecretowsdlPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public ConsultaDecretowsdlPortType getConsultaDecretowsdlPortType() {
    if (consultaDecretowsdlPortType == null)
      _initConsultaDecretowsdlPortTypeProxy();
    return consultaDecretowsdlPortType;
  }

	@Override
	public String consultaDecreto(String iue, String nro_decreto) throws RemoteException {
		return consultaDecretowsdlPortType.consultaDecreto(iue, nro_decreto);
	}
  
  
}