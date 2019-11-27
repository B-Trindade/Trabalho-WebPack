import React, { Component } from 'react';

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            items: [],
            isLoaded: false,
        }
    }
    
    componentDidMount() { 
        const saidas_url = "https://jsonplaceholder.typicode.com/users";

        //ALTERNATIVE METHOD:

        //async function saidasAPI(url) {
        //    const response = await fetch(url);
        //    const data = await response.json();
        //    
        //    console.log()
        //}
        //
        //saidasAPI(saidas_url);

        //KNOWN METHOD:
        fetch(saidas_url)
            .then(res => res.json())
            .then(json => {
                this.setState({
                    isLoaded: true,
                    items: json,
                })
            });

    }
    render() {
        var { isLoaded, items} = this.state;

        if(!isLoaded) {
            return <div>Loading...</div>;
        } else {
            return (
                <ul>
                    {items.map(item => (
                        <li key={item.id}> 
                            Nome: {item.name} | Email: {item.email}
                        </li>
                    ))}
                </ul>
            );
        }
    }
}

export default App;


