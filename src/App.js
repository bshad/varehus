import React, {Component} from 'react';
import './App.css';
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link
} from "react-router-dom";

class Form extends Component {
    state = {
        navn: '',
        vekt: '',
        pris: '',
        antall: ''
    };

    handleSubmit = async (event) => {
        event.preventDefault();
        const vare = {
            id: null,
            navn: this.state.navn,
            vekt: parseFloat(this.state.vekt),
            pris: parseFloat(this.state.pris),
            antall: parseInt(this.state.antall)
        }

        console.log("VARE : ", vare);

        const resp = await fetch('http://localhost:8080/vare/', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(vare)
            }
        )
        this.props.onSubmit();
        this.setState({
            navn: '',
            vekt: '',
            pris: '',
            antall: ''
        })
    };

    render() {
        return (
            <form onSubmit={this.handleSubmit}>
                <input
                    type="text"
                    value={this.state.navn}
                    onChange={event => this.setState({navn: event.target.value})}
                    placeholder="Vare navn"
                    required
                />

                <input
                    type="number"
                    value={this.state.vekt}
                    onChange={event => this.setState({vekt: event.target.value})}
                    placeholder="Vare vekt"
                    required
                />

                <input
                    type="number"
                    value={this.state.pris}
                    onChange={event => this.setState({pris: event.target.value})}
                    placeholder="Vare pris"
                    required
                />

                <input
                    type="number"
                    value={this.state.antall}
                    onChange={event => this.setState({antall: event.target.value})}
                    placeholder="Vare antall"
                    required
                />
                <button>Legg til vare</button>
            </form>
        );
    }
}

class Vare extends Component {

    delete = async () => {
        const resp = await fetch('http://localhost:8080/vare/', {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(this.props.id)
            }
        )
        this.props.onDelete();
    }

    render() {
        const vare = this.props;
        return (
            <tr>
                <td>{vare.id}</td>
                <td>{vare.navn}</td>
                <td>{vare.vekt}</td>
                <td>{vare.pris}</td>
                <td>{vare.antall}</td>
                <td><button onClick={this.delete}>Slett</button></td>
                <td><button>Rediger</button></td>
            </tr>
        );
    }
}

const VareListe = (props) => (
    <table>
        <thead>
        <tr>
            <th>id</th>
            <th>navn</th>
            <th>vekt</th>
            <th>pris</th>
            <th>antall</th>
        </tr>
        </thead>
        <tbody>
        {
            props.items.map(item =>
                <Vare key={item.id} onDelete={props.onDelete} {...item}/>
            )
        }
        </tbody>
    </table>
)

class App extends Component {

    state = {
        items: []
    };

    getAllItems = () => {
        fetch("http://localhost:8080/vare/list")
            .then(data => data.json())
            .then(
                data => {
                    console.log("data: ", data);
                    this.setState(prevItems => ({items: data}));
                }
            )
            .catch(error => console.log("error: ", error))
    }

    componentDidMount() {
        this.getAllItems();
    }

    render() {
        return (
            <div className="App">
                <header className="App-header">
                    <div>Varehus</div>
                    <Form onSubmit={this.getAllItems}/>
                    <VareListe items={this.state.items} onDelete={this.getAllItems}/>
                </header>
            </div>
        );
    }
}

export default App;
