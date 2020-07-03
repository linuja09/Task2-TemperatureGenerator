package temperatureconverter

/**
 * class : HomeController
 * Implements the logic to convert the temperature
 */
class HomeController {
    def temperatureService

    def index = {
        redirect action: "convert"
    }

    def convert = {
        def data = []

        def responseData = [
                'convertedFrom': params.convertedFrom,
                'resultUnit': params.resultUnit,
                'base': params.base,
                'results': params.results,
                'status': params.status
        ]

        data.push(responseData)

        [converted: data]
    }

    /**
     * Calls 'temperatureService' method to convert the temperature and returns the result
     */
    def convertTemp = {
        def result = temperatureService.convertTemperature(params.temperature, params.unit);

        if(result){
            redirect(action:"convert", params: [
                    convertedFrom: result.convertedFrom,
                    resultUnit: result.resultUnit,
                    base: result.base,
                    results: result.results,
                    status: result.status
            ])
        }
    }
}
