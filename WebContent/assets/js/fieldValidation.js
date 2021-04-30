

    var form = document.querySelector(".js-pristine-validation");

    if (form) {

      // create the pristine instance
      var pristine = new Pristine(form);

      form.addEventListener('submit', function (e) {
         e.preventDefault();
         
         // check if the form is valid
         var valid = pristine.validate(); // returns true or false

         if (valid) {
         	form.submit();
         }

      });
    }
