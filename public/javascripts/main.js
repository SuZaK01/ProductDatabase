/**
 * Created by lukasz on 30.09.16.
 */
function del(urlToDelete){
    $.ajax({
        url: urlToDelete,
        type: 'DELETE',
        success: function(results){
            location.reload();
        }
    });
}