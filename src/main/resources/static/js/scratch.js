function deleteMovie(movieUUID) {
    axios.get('http://localhost:8086/movies/deleteMovie', {
    params: {
        movieUUID: movieUUID
    }
    })
    .then(function (response) {
        document.getElementById("message").textContent = 'Action successfully done.';
        document.getElementById('id01').style.display='block';
    })
    .catch(function (error) {
    document.getElementById("message").textContent = 'Object not found!';
        document.getElementById('id01').style.display='block';
    });
  }