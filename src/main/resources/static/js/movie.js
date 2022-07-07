function deleteMovieAskForConfirmation(movieUUID) {
        document.getElementById('message').textContent = 'Are you sure to proceed with the deletion of movie with UUID ' + movieUUID + '?';
        document.getElementById('id01').style.display='block';
        document.getElementById('confirm').onclick = function(){ document.getElementById('id01').style.display='none'; deleteMovieConfirmed(movieUUID); };
}

function deleteMovieConfirmed(movieUUID) {
    axios.delete('https://filmstreamer.herokuapp.com/api/movies/deleteMovie', {
    params: { movieId: movieUUID }
    })
    .then(function (response) {
        document.getElementById('message').textContent = 'Action successfully done. Deleted film: ' + response.data.title + ', with UUID ' + response.data.movieUUID;
        document.getElementById('id01').style.display='block';
        document.getElementById('confirm').onclick = function(){ document.getElementById('id01').style.display='none'; location.replace('https://filmstreamer.herokuapp.com//movies/movies');};
    })
    .catch(function (error) {
        document.getElementById('message').textContent = 'Object not found!';
        document.getElementById('id01').style.display='block';
        document.getElementById('confirm').onclick = function(){ document.getElementById('id01').style.display='none'; location.replace('https://filmstreamer.herokuapp.com//movies/movies');};
    });
  }