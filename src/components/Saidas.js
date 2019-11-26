import React, { Component } from 'react';
import "regenerator-runtime/runtime";


class Saidas extends Component {
    render() {
        return saidasAPI()
    }
}

async function saidasAPI() {
    const saidas_url = "https://api.github.com/users/collbreno";
    const response = await fetch(saidas_url);
    const data = await response.json();
    const login = data.login;
    return(<p>[{data}]</p>);
}

export default Saidas;