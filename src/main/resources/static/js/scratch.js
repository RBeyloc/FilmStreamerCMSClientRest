function deleteMovieAskForConfirmation(movieUUID) {
        document.getElementById('message').textContent = 'WARNING: Are you sure to proceed?';
        document.getElementById('id01').style.display='block';
        document.getElementById('confirm').onclick = function(){ document.getElementById('id01').style.display='none'; deleteMovieConfirmed(movieUUID); };
}

function deleteMovieConfirmed(movieUUID) {
    axios.get('http://localhost:8086/movies/deleteMovie', {
    params: {
        movieUUID: movieUUID
    }
    })
    .then(function (response) {
        document.getElementById('message').textContent = 'Action successfully done.';
        document.getElementById('id01').style.display='block';
        document.getElementById('confirm').onclick = function(){ document.getElementById('id01').style.display='none'; location.replace('http://localhost:8086/movies/movies');};
    })
    .catch(function (error) {
    document.getElementById('message').textContent = 'Object not found!';
        document.getElementById('id01').style.display='block';
        document.getElementById('confirm').onclick = function(){ document.getElementById('id01').style.display='none'; location.replace('http://localhost:8086/movies/movies');};
    });
  }