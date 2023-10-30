

    var qustate = document.querySelector('.question-state');
    var questionforms = document.querySelectorAll('.question-list-form');

    qustate.addEventListener('change', function () {
        qustatevalue = qustate.value;

        questionforms.forEach(function (form) {
            var statecollum = form.querySelector('.question-list-collum7');
            var vquestionform = statecollum.textContent.trim();

            if (qustatevalue === "0") {
            form.style.display = "block";
            form.style.display = "flex";
        }
        else if (qustatevalue === "1") {
            if (vquestionform === "완료") {
                form.style.display = "block";
                form.style.display = "flex";
            }
            else {
                form.style.display = "none";
            }
        }
        else if (qustatevalue === "2") {
            if (vquestionform === "미완료") {
                form.style.display = "block";
                form.style.display = "flex";
            }
            else {
                form.style.display = "none";
            };

        }

        });
     });


