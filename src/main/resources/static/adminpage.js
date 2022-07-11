const url = 'http://localhost:8080/admin/adminpage/'
const update_url = 'http://localhost:8080/admin/adminpage/edit?listRoles='
const delete_url = 'http://localhost:8080/admin/adminpage/'
const add_url = 'http://localhost:8080/admin/adminpage/new?listRoles='
const get_user = 'http://localhost:8080/admin/adminpage/getUser/'

//const addUserForm = document.querySelector('#addUser')
const editUserForm = document.querySelector('#modalEdit')
const deleteUserForm = document.querySelector('#modalDelete')
let currentUserId = null


function getAllUsers() {
    fetch(url)
        .then(res => res.json())
        .then(users => {
            let temp = '';
            users.forEach(function (user) {
                temp += `
                  <tr>
                        <td>${user.id}</td>
                        <td>${user.name}</td>
                        <td>${user.surname}</td>
                        <td>${user.age}</td>
                        <td>${user.username}</td>
                        <td>${user.roles.map(role => role.name === 'USER' ? ' USER' : ' ADMIN')}</td>
                  <td>
                       <button type="button" data-userid="${user.id}" data-action="edit" class="btn btn-info"
                        data-toggle="modal" data-target="modal" id="edit-user" data-id="${user.id}">Edit</button>
                   </td>
                   <td>
                       <button type="button" class="btn btn-danger" id="delete-user" data-action="delete"
                       data-id="${user.id}" data-target="modal">Delete</button>
                        </td>
                  </tr>`;
            });
            document.querySelector('#allUsers').innerHTML = temp;
        });
}

getAllUsers()


function newUser() {
    fetch(add_url + $("#roles1").val(), {
        method: 'POST',
        headers: {'Content-Type': 'application/json; charset=utf-8'},
        body: JSON.stringify({
            name: document.getElementById('name1').value,
            surname: document.getElementById('surname1').value,
            age: document.getElementById('age1').value,
            username: document.getElementById("username1").value,
            password: document.getElementById('password1').value

        })
    }).then(response => response.json())
        .then(data => {
            getAllUsers(data);
        })
        .then(() => {
            document.getElementById('add_new_user').click()

        })
}

const on = (element, event, selector, handler) => {
    element.addEventListener(event, e => {
        if (e.target.closest(selector)) {
            handler(e)
        }
    })
}

////////////// EDIT user////////////////
const formEditUser = document.getElementById("formEditUser");
on(document, 'click', '#edit-user', e => {
    const userInfo = e.target.parentNode.parentNode
    currentUserId = userInfo.children[0].innerHTML
    fetch(get_user + currentUserId)
        .then(res => res.json())
        .then(user => {
            document.querySelector('#id2').value = currentUserId;
            formEditUser.name.value = user.name;
            formEditUser.surname.value = user.surname;
            formEditUser.age.value = user.age;
            formEditUser.username.value = user.username;
            formEditUser.password.value = user.password;
        });
    $("#modalEdit").modal("show")
})

editUserForm.addEventListener('submit', () => {
    fetch(update_url + $("#roles2").val(), {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id: document.getElementById('id2').value,
            name: document.getElementById('name2').value,
            surname: document.getElementById('surname2').value,
            age: document.getElementById('age2').value,
            username: document.getElementById("username2").value,
            password: document.getElementById('password2').value
        })
    }).then(() => $("#modalEdit").modal("hide"))

})

// /////////Delete user/////////////////////////////////
deleteUserForm.addEventListener('submit', () => {
    fetch(delete_url + currentUserId, {
        method: 'DELETE'
    })
        .then(() => $("#modalDelete").modal("hide"))
})

on(document, 'click', '#delete-user', e => {
    const userDelete = e.target.parentNode.parentNode
    currentUserId = userDelete.children[0].innerHTML

    document.getElementById('id3').value = userDelete.children[0].innerHTML
    document.getElementById('name3').value = userDelete.children[1].innerHTML
    document.getElementById('surname3').value = userDelete.children[2].innerHTML
    document.getElementById('age3').value = userDelete.children[3].innerHTML
    document.getElementById('username3').value = userDelete.children[4].innerHTML
    $("#modalDelete").modal("show")
})