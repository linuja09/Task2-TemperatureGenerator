<!DOCTYPE html>
    <html>
    <head>
    <meta name="layout"content="main"/>
    <title>Temperature converter</title>
    </head>
    <body>
    <div class="jumbotron">
    <h1 class="display-4">Hello,Guest!</h1>
    <hr class="my-4">

    <g:form name="convertForm"action="convertTemp">
    <div class="form-row">
    <div class="form-group col-md-6 ">

    <g:select class="custom-select mr-sm-2"name="unit" from="${['Celsius','Fahrenheit']}"/>

    </div>
    <div class="form-group col-md-6 ">
    <g:textField name="temperature"class="form-control"placeholder="Enter the Temperature"/>

    </div>

    </div>
    <div class="form-row">
    <div class="col">
    <button type="submit"class="btn btn-primary">Submit</button>
    </div>

    </div>
    </g:form>



    </div>

    <g:each in="${converted}" var='result'>
      <div class="alert alert-secondary"role="alert">
    <span class="float-right">Date</span>
        <B>${result.convertedFrom} to ${result.resultUnit}</B>
    <div>${result.results}</div>
      </div>
    </g:each>







    </body>
    </html>