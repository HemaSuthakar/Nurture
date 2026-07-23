import { useParams } from 'react-router-dom';
import { TrainersData } from '../TrainersMock';

function TrainerDetail() {
    const { id } = useParams();
    const trainer = TrainersData.find(t => t.trainerId === id);

    if (!trainer) {
        return <p>Trainer not found.</p>;
    }

    return (
        <div>
            <h2>Trainer Details</h2>
            <p><strong>Trainer ID:</strong> {trainer.trainerId}</p>
            <p><strong>Name:</strong> {trainer.name}</p>
            <p><strong>Email:</strong> {trainer.email}</p>
            <p><strong>Phone:</strong> {trainer.phone}</p>
            <p><strong>Stream:</strong> {trainer.technology}</p>
            <p><strong>Skills:</strong> {trainer.skills.join(', ')}</p>
        </div>
    );
}

export default TrainerDetail;
