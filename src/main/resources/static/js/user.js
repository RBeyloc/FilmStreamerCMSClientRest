function deleteUserAskForConfirmation(userUUID) {
        document.getElementById('message').textContent = 'Are you sure to proceed with the deletion of user with UUID ' + userUUID + '?';
        document.getElementById('id01').style.display='block';
        document.getElementById('confirm').onclick = function(){ document.getElementById('id01').style.display='none'; deleteUserConfirmed(userUUID); };
}

function deleteUserConfirmed(userUUID) {
    axios.delete('https://filmstreamer.herokuapp.com/api/users/deleteUser', {
    params: { userId: userUUID }
    })
    .then(function (response) {
        document.getElementById('message').textContent = 'Action successfully done. Deleted user: ' + response.data.title + ', with UUID ' + response.data.userUUID;
        document.getElementById('id01').style.display='block';
        document.getElementById('confirm').onclick = function(){ document.getElementById('id01').style.display='none'; location.replace('users');};
    })
    .catch(function (error) {
        document.getElementById('message').textContent = 'Object not found!';
        document.getElementById('id01').style.display='block';
        document.getElementById('confirm').onclick = function(){ document.getElementById('id01').style.display='none'; location.replace('users');};
    });
  }