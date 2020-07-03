package temperatureconverter

import wslite.soap.SOAPClient
import grails.gorm.transactions.Transactional

/**
 * class : TemperatureService
 * Implements the logic to convert the temperature using webservice
 */
@Transactional
class TemperatureService {

    def convertTemperature(String temperature, String unit) {

        def client = new SOAPClient("http://q88.com/WS/Q88WSInternal.asmx")

        def response = client.send(
                """<?xml version="1.0" encoding="utf-8"?>
                    <soap12:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://www.w3.org/2003/05/soap-envelope">
                      <soap12:Body>
                        <ConvertTemperature xmlns="http://q88.com/webservices/">
                          <property>"""+unit+"""</property>
                          <val>"""+temperature+"""</val>
                        </ConvertTemperature>
                      </soap12:Body>
                    </soap12:Envelope>"""
        )

        def result = response.envelope.toString()
        def converted

        if(unit == "Celsius"){
            converted = result.substring(temperature.length())
        }
        else {
            converted = result.substring(0, result.length()-temperature.length())
        }

        def responseData = [
                'convertedFrom': unit,
                'resultUnit': unit == "Celsius" ? "Fahrenheit" : "Celsius",
                'base': temperature,
                'results': converted,
                'status': response.httpResponse.statusCode
        ]

        return  responseData

    }

}
