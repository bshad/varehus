import React, {Component} from "react";

class VareBilde extends Component {

    delete = async () => {
        const resp = await fetch('http://localhost:8080/varebilder/'+this.props.vareid+'/'+this.props.id, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                }
            }
        )
        this.props.onDelete();
    }

    render() {
        const bilde = this.props;
        return (
            <div>
                <img src={`data:image/jpeg;base64,${bilde.bilde}`}/>
                <button onClick={this.delete}>Slett bilde</button>
            </div>
        );
    }
}

const VareBildeListe = (props) => (
    props.bilder.map(item =>
        <VareBilde key={item.id} onDelete={props.onDelete} vareid={props.vareid} {...item}/>
    )
)

class VareBilder extends Component {

    state = {
        filename: '',
        file: '',
        bilder: []
    }

    handleSubmit = async (event) => {
        event.preventDefault();
        const data = new FormData();
        data.append('file', this.state.file)
        fetch('http://localhost:8080/varebilder/' + this.props.vareId, {
            method: 'POST',
            body: data
        });
        this.getAllImages();
    }

    getAllImages = () => {
        fetch("http://localhost:8080/varebilder/" + this.props.vareId)
            .then(data => data.json())
            .then(
                data => {
                    console.log("VareBilder getAllImages: ", data);
                    this.setState({bilder: data})
                }
            )
            .catch(error => console.log("error: ", error))
    }

    componentDidMount() {
        this.getAllImages();
    }

    render() {
        return (
            <div>
                <div>Varebilder</div>
                <form onSubmit={this.handleSubmit}>
                    <input type="file" name="file"
                           value={this.state.filename}
                           onChange={event => this.setState({
                               filename: event.target.value,
                               file: event.target.files[0]
                           })}
                    />
                    <button>Legg til bilde</button>
                </form>
                <VareBildeListe vareid={this.props.vareId} bilder={this.state.bilder} onDelete={this.getAllImages}/>
            </div>
        );
    }
}

export default VareBilder;