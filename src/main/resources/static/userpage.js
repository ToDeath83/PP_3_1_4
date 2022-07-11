const urlUser = 'http://localhost:8080/user/userpage/'
let loggedInUser = document.querySelector('#UserInfo');


fetch(urlUser)
    .then(res => res.json())
    .then(data => {
        loggedInUser.innerHTML = `
                                <td>${data.id}</td>
                                <td>${data.name}</td>
                                <td>${data.surname}</td>
                                <td>${data.age}</td>
                                <td>${data.username}</td>
                                <td>${data.roles.map(role => role.name === 'USER' ? ' USER' : ' ADMIN')}</td>
                                `;
    })