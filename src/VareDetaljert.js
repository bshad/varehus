import {useParams} from "react-router-dom";
import React, {Component, useEffect, useState} from "react";

class EditForm extends Component {

    state = {
        id: '',
        navn: '',
        vekt: '',
        pris: '',
        antall: ''
    };

    fetchVareDetaljer = async (vareId) => {
        fetch("http://localhost:8080/vare/" + vareId)
            .then(data => data.json())
            .then(
                data => {
                    console.log("data: ", data);
                    this.setState(
                        {
                            id: data.id,
                            navn: data.navn,
                            vekt: data.vekt,
                            pris: data.pris,
                            antall: data.antall
                        }
                    );
                }
            )
            .catch(error => console.log("error: ", error))
        this.props.onSave();
    }

    componentDidMount() {
        this.fetchVareDetaljer(this.props.vareId);
    }

    handleSubmit = async (event) => {
        event.preventDefault();
        const vare = {
            id: this.state.id,
            navn: this.state.navn,
            vekt: parseFloat(this.state.vekt),
            pris: parseFloat(this.state.pris),
            antall: parseInt(this.state.antall)
        }

        const resp = await fetch('http://localhost:8080/vare/', {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(vare)
            }
        )
        this.fetchVareDetaljer(this.props.vareId);
    };

    render() {
        return (
            <form onSubmit={this.handleSubmit}>
                <div>{this.state.id}</div>
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
                <button>Lagre endringer</button>
            </form>
        );
    }
}


class VareHistorikk extends Component {
    render() {
        const historikk = this.props;
        return (
            <tr>
                <td>{historikk.feltnavn}</td>
                <td>{historikk.gammelt}</td>
                <td>{historikk.ny}</td>
                <td>{historikk.dato}</td>
            </tr>
        );
    }
}

const VareHistorikkListe = (props) => (
    <div>
        De siste 5 endringene for denne varen
        <table>
            <thead>
            <tr>
                <th>feltnavn</th>
                <th>gammelt verdi</th>
                <th>ny verdi</th>
                <th>dato</th>
            </tr>
            </thead>
            <tbody>
            {
                props.vareHistorikk.map(item =>
                    <VareHistorikk key={item.id} {...item}/>
                )
            }
            </tbody>
        </table>
    </div>
)

function VareDetaljert() {
    let {vareid} = useParams();
    const [vareHistorikk, setVareHistorikk] = useState([]);

    function fetchVareHistorikk(){

    // fetchVareHistorikk = () => {
        fetch("http://localhost:8080/varehistorikk/"+vareid)
            .then(data => data.json())
            .then(
                data => {
                    console.log("varehistorikk data: ", data);
                    setVareHistorikk(data);
                }
            )
            .catch(error => console.log("error: ", error))
    }

    useEffect(() => {
        fetchVareHistorikk()
    }, []);

    return (
        <div>
            <EditForm vareId={vareid} onSave={fetchVareHistorikk}/>
            <VareHistorikkListe vareHistorikk={vareHistorikk}/>
        </div>
    );
}

export default VareDetaljert;