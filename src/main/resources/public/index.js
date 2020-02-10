$(document).ready(function() {
    // Add a div to append to results.
    $('#body').append("<div id='scratch'></div>");

    function onSuccess(data) {
        // Remove all of the scratch DOM children.
        var myNode = $('#scratch');
        myNode.empty();

        // Add the new data
        myNode.append(data)

        // Remove all links from the data.
        var links = $('a');
        links.remove();

        // Bind buddy submit button.
        $('#buddy').submit(onSubmit);
    };

    // Override submit with ajax calls to the same URIs.
    function onSubmit(event) {
        event.preventDefault();
        var url=$(this).closest('form').attr('action'),
            data=$(this).closest('form').serialize(),
            type=$(this).closest('form').attr('method');
        console.log(url);
        $.ajax({
            url:url,
            type:type,
            data:data,
            success:onSuccess
        });

    };

    // Override all forms.
    $("#view").submit(onSubmit);
    $("#add").submit(onSubmit);
    $("#create").submit(onSubmit);
});