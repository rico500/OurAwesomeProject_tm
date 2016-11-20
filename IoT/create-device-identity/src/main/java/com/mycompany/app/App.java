package com.mycompany.app;

import com.microsoft.azure.iot.service.exceptions.IotHubException;
import com.microsoft.azure.iot.service.sdk.Device;
import com.microsoft.azure.iot.service.sdk.RegistryManager;

import java.io.IOException;
import java.net.URISyntaxException;


/**
 * Hello world!
 *
 */
public class App 
{
    private static final String connectionString = "HostName=Future-Gadget-3.azure-devices.net;SharedAccessKeyName=iothubowner;SharedAccessKey=j53YNZZir1d/dQ9z6UO53mvJxlmGmT+DlAzGQ04s44A=";
    private static final String deviceId = "FutureGadget4";

    public static void main( String[] args ) throws IOException, URISyntaxException, Exception
    {
         RegistryManager registryManager = RegistryManager.createFromConnectionString(connectionString);

    Device device = Device.createFromId(deviceId, null, null);
    try {
        device = registryManager.addDevice(device);
    } catch (IotHubException iote) {
        try {
           device = registryManager.getDevice(deviceId);
        } catch (IotHubException iotf) {
           iotf.printStackTrace();
        }
    }
    System.out.println("Device id: " + device.getDeviceId());
    System.out.println("Device key: " + device.getPrimaryKey());

    }
}
